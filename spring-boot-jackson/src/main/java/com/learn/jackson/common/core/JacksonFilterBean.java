package com.learn.jackson.common.core;

import com.fasterxml.jackson.annotation.JsonFilter;

public class JacksonFilterBean {

	/** @Fields INCOME_PARAMETER_INCLUDE_FILTER : TODO */
	public static final String INCOME_PARAMETER_INCLUDE_FILTER = "income_include_filter";

	/** @Fields INCOME_PARAMETER_EXCLUDE_FILTER : TODO */
	public static final String INCOME_PARAMETER_EXCLUDE_FILTER = "income_exclude_filter";

	/** @Fields OUTCOME_PARAMETER_INCLUDE_FILTER : TODO */
	public static final String OUTCOME_PARAMETER_INCLUDE_FILTER = "outcome_include_filter";

	/** @Fields OUTCOME_PARAMETER_EXCLUDE_FILTER : TODO */
	public static final String OUTCOME_PARAMETER_EXCLUDE_FILTER = "outcome_exclude_filter";
	

	@JsonFilter(INCOME_PARAMETER_INCLUDE_FILTER)
	public interface IncomeParameterIncludeFillter {

	}

	@JsonFilter(INCOME_PARAMETER_EXCLUDE_FILTER)
	public interface IncomeParameterExcludeFillter {

	}

	@JsonFilter(OUTCOME_PARAMETER_INCLUDE_FILTER)
	public interface OutcomeParameterIncludeFillter {

	}

	@JsonFilter(OUTCOME_PARAMETER_EXCLUDE_FILTER)
	public interface OutcomeParameterExcludeFillter {

	}

}
