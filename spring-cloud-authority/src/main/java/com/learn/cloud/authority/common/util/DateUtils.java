package com.learn.cloud.authority.common.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static Date getCurrentDate() {
		return new Date();
	}
	
	public static void main(String[] args) {
		String str = DateFormatUtils.formatUTC(getCurrentDate(), "yyyy-MM-dd  hh:mm:ss");
		System.out.println(str);
	}

}
