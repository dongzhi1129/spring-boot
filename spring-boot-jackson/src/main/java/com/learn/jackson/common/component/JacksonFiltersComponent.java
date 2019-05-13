package com.learn.jackson.common.component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.learn.jackson.common.annotation.JacksonFilter;
import com.learn.jackson.common.annotation.JacksonFilters;
import com.learn.jackson.common.core.JacksonFilterBean;
import com.learn.jackson.common.core.JacksonFilterBean.IncomeParameterExcludeFillter;
import com.learn.jackson.common.core.JacksonFilterBean.IncomeParameterIncludeFillter;
import com.learn.jackson.common.core.JacksonFilterBean.OutcomeParameterExcludeFillter;
import com.learn.jackson.common.core.JacksonFilterBean.OutcomeParameterIncludeFillter;

@Component
@Aspect
public class JacksonFiltersComponent {

	private static Logger log = LoggerFactory.getLogger(JacksonFiltersComponent.class);

	/**
	 * @Title: jacksonFilterPointCut
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return void
	 */
	@Pointcut("@annotation(com.learn.jackson.common.annotation.JacksonFilters) "
			+ "|| @annotation(com.learn.jackson.common.annotation.JacksonFilter)")
	public void jacksonFiltersPointCut() {
	}

	/**
	 * @Title: jacksonFilter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return void
	 * @throws Throwable
	 */
	@Around(value = "jacksonFiltersPointCut()")
	public Object jacksonFiltersAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Parameter[] parameters = method.getParameters();
		// 获取注解
		JacksonFilter jacksonFilterElem = method.getAnnotation(JacksonFilter.class);
		JacksonFilter[] jacksonFiterArray = null;
		JacksonFilters jacksonFilters = null;
		if (jacksonFilterElem != null) {
			jacksonFiterArray = new JacksonFilter[1];
			jacksonFiterArray[0] = jacksonFilterElem;
		} else if ((jacksonFilters = method.getAnnotation(JacksonFilters.class)) != null) {
			jacksonFiterArray = jacksonFilters.value();
		}
		List<JacksonFilter> requestFilters = new ArrayList<>();
		List<JacksonFilter> responseFilters = new ArrayList<>();
		if (jacksonFiterArray == null || jacksonFiterArray.length <= 0) {
			return joinPoint.proceed();
		}
		// 对于含有多个输入VO时，过滤参数要考虑参数的顺序问题，当前只支持一个VO参数
		// 解析出request请求和response请求
		requestFilters = Arrays.stream(jacksonFiterArray)
				.filter(jacksonFilter -> jacksonFilter.type() == JacksonFilter.JscksonFilterType.REQUEST)
				.collect(Collectors.toList());
		responseFilters = Arrays.stream(jacksonFiterArray)
				.filter(jacksonFilter -> jacksonFilter.type() == JacksonFilter.JscksonFilterType.RESPONSE)
				.collect(Collectors.toList());
		// 处理request前置通知
		Object[] proprocessArgs = jacksonRequestFilterHanlder(joinPoint, requestFilters, parameters);
		// 方法执行
		Object result = (proprocessArgs == null) ? joinPoint.proceed() : joinPoint.proceed(proprocessArgs);
		// 处理response通知
		return jacksonResponseFilterHanlder(joinPoint, responseFilters, result);

	}

	/**
	 * 获取request过滤的对象数组，用于传递到下一个通知中去，只支持包含一个REQUEST注解
	 * 
	 * @Title: jacksonRequestFilterHanlder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return Object[] 如果为null,不做前置通知处理
	 */
	private Object[] jacksonRequestFilterHanlder(ProceedingJoinPoint joinPoint, List<JacksonFilter> requestFilters,
			Parameter[] parameters) throws IOException {
		// 只支持参数为一个,如果含有多个参数，要解决方法参数列表中，哪个参数的值为要过滤的类型，joinPoint.getArgs(),不能确定参数类型
		Object[] methodArgs = joinPoint.getArgs();
		if (requestFilters == null || requestFilters.size() != 1 || methodArgs == null || methodArgs.length != 1) {
			return null;
		}
		JacksonFilter jacksonFilter = requestFilters.get(0);
		// 获取解析的要过滤的class
		final Class<?> clazz = jacksonFilter.value();
		if (clazz == null) {
			return null;
		}
		// 判断类型参数是否包含此class
		if (parameters == null || parameters.length <= 0
				|| Arrays.asList(parameters).stream().noneMatch(parameter -> parameter.getType() == clazz)) {
			return null;
		}
		// 判断对应参数值是否合法，暂时只支持参数列表为一个的情况，对于多个的情况，要过滤的VO必须放在第一个
		// 取第一个作为参数值

		// 判断要include filter和exclude filter过滤
		// 获取注解参数,不包含父类参数(对于继承暂时不考虑,继承类要考虑参数相同的情况，)
		Field[] fields = getClassFields(clazz);
		final String[] includeFileds = jacksonFilter.include();
		final String[] excludeFileds = jacksonFilter.exclude();

		// 获取可以进行过滤的属性，優先使用inlucde中屬性，如果inlucde為空，使用exclude屬性,(如果都不为空使用include)
		Set<String> inclueFiltFields = getFilterFields(fields, includeFileds);
		;
		Set<String> exclueFiltFields = getFilterFields(fields, excludeFileds);
		;
		if (inclueFiltFields == null && exclueFiltFields == null) {
			return null;
		}
		// 处理request参数
		ObjectMapper objectMapper = null;
		if (inclueFiltFields != null) {
			objectMapper = getMixObjectMapper(clazz, IncomeParameterIncludeFillter.class, inclueFiltFields,
					JacksonFilterBean.INCOME_PARAMETER_INCLUDE_FILTER, true);
		} else if (exclueFiltFields != null) {
			objectMapper = getMixObjectMapper(clazz, IncomeParameterExcludeFillter.class, exclueFiltFields,
					JacksonFilterBean.INCOME_PARAMETER_EXCLUDE_FILTER, false);
		} else if (objectMapper == null) {
			return null;
		}
		// 对于多个参数，获取类型clazz，的值，依次更改Object的值
		String argsJson = objectMapper.writeValueAsString(joinPoint.getArgs()[0]);
		Object[] filterArgs = new Object[] { objectMapper.readValue(argsJson, clazz) };
		log.debug("Request method =[{}],parameter=[{}],filterParameter=[{}]", joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()), argsJson);
		return filterArgs;
	}

	/**
	 * 咱只支持一个的情况
	 * 
	 * @Title: jacksonResponseFilterHanlder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return ProceedingJoinPoint
	 */
	private Object jacksonResponseFilterHanlder(ProceedingJoinPoint joinPoint, List<JacksonFilter> responseFilters,
			Object result) throws IOException {
		// 只支持参数为一个VO的情况
		if (responseFilters == null || responseFilters.size() != 1) {
			return result;
		}
		JacksonFilter jacksonFilter = responseFilters.get(0);
		// 获取解析的要过滤的class
		final Class<?> clazz = jacksonFilter.value();
		if (clazz == null) {
			return result;
		}
		// 判断要include filter和exclude filter过滤
		// 获取注解参数,不包含父类参数(对于集成暂时不考虑)
		Field[] fields = getClassFields(clazz);
		final String[] includeFileds = jacksonFilter.include();
		final String[] excludeFileds = jacksonFilter.exclude();
		JacksonFilter.JscksonFilterType type = jacksonFilter.type();

		// 获取可以进行过滤的属性，優先使用inlucde中屬性，如果inlucde為空，使用exclude屬性,(如果都不为空使用include)
		Set<String> inclueFiltFields = getFilterFields(fields, includeFileds);
		Set<String> exclueFiltFields = getFilterFields(fields, excludeFileds);
		if (inclueFiltFields == null && exclueFiltFields == null) {
			return result;
		}
		// 处理request参数
		ObjectMapper objectMapper = null;
		if (inclueFiltFields != null) {
			objectMapper = getMixObjectMapper(clazz, OutcomeParameterIncludeFillter.class, inclueFiltFields,
					JacksonFilterBean.OUTCOME_PARAMETER_INCLUDE_FILTER, true);
		} else if (exclueFiltFields != null) {
			objectMapper = getMixObjectMapper(clazz, OutcomeParameterExcludeFillter.class, exclueFiltFields,
					JacksonFilterBean.OUTCOME_PARAMETER_EXCLUDE_FILTER, false);
		} else if (objectMapper == null) {
			return result;
		}
		String argsJson = objectMapper.writeValueAsString(result);
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		return objectMapper.readValue(argsJson, methodSignature.getReturnType());
	}

	private Field[] getClassFields(Class<?> clazz) {
		if(clazz == null) {
			return null;
		}
		return FieldUtils.getAllFields(clazz);

	}

	/**
	 * 
	 * @Title: getFilterFields
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param fields:object
	 * @includeFileds field must filter
	 * 
	 * @return Set<String>
	 */
	private Set<String> getFilterFields(Field[] fields, final String[] toBeFilterFileds) {
		if (fields == null || fields.length <= 0 || toBeFilterFileds == null || toBeFilterFileds.length <= 0) {
			return null;
		}
		Set<String> inclueFiltFields = Arrays.stream(fields)
				.filter(filed -> Arrays.asList(toBeFilterFileds).contains(filed.getName()))
				.map(filed -> filed.getName()).collect(Collectors.toSet());
		return inclueFiltFields;

	}

	private ObjectMapper getMixObjectMapper(Class<?> toBeMixVo, Class<?> toBeMixFilter, Set<String> toBeFilterFields,
			String filterId, boolean isInclude) {
		ObjectMapper objectMapper = new ObjectMapper();
		if (isInclude) {
			objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter(filterId,
					SimpleBeanPropertyFilter.filterOutAllExcept(toBeFilterFields)));
		} else {
			objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter(filterId,
					SimpleBeanPropertyFilter.serializeAllExcept(toBeFilterFields)));
		}
		objectMapper.addMixIn(toBeMixVo, toBeMixFilter);
		return objectMapper;
	}
}
