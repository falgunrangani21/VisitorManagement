package com.efive.VisitorManagement.common;

import java.util.Calendar;
import java.util.Random;

public class CommonUtils {

	public String pwdgen = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@#$%^&+=*";
	public String pwdpattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";

	// for second to get txtn
	public static long getTxnnumber() {
		Calendar calendar = Calendar.getInstance();
		String year = "" + calendar.get(Calendar.YEAR);
		String month = "" + (calendar.get(Calendar.MONTH) + 1);
		String day = "" + calendar.get(Calendar.DATE);
		String hour = "" + calendar.get(Calendar.HOUR);
		String minutes = "" + calendar.get(Calendar.MINUTE);
		String second = "" + calendar.get(Calendar.SECOND);
		StringBuilder sbNumber = new StringBuilder("" + year);
		if (month.length() == 1)
			month = "0" + month;
		if (day.length() == 1)
			day = "0" + day;
		if (hour.length() == 1)
			hour = "0" + hour;
		if (minutes.length() == 1)
			minutes = "0" + minutes;
		if (second.length() == 1)
			second = "0" + second;
		sbNumber.append(month);
		sbNumber.append(day);
		sbNumber.append(hour);
		sbNumber.append(minutes);
		sbNumber.append(second);

		return Long.parseLong(sbNumber.toString());
	}

	// for milli seconds to get txtn
	public static long getTxnnumberms() {
		Calendar calendar = Calendar.getInstance();
		String year = "" + calendar.get(Calendar.YEAR);
		String month = "" + (calendar.get(Calendar.MONTH) + 1);
		String day = "" + calendar.get(Calendar.DATE);
		String hour = "" + calendar.get(Calendar.HOUR);
		String minutes = "" + calendar.get(Calendar.MINUTE);
		String second = "" + calendar.get(Calendar.SECOND);
		String milisecond = "" + calendar.get(Calendar.MILLISECOND);
		StringBuilder sbNumber = new StringBuilder("" + year);
		if (month.length() == 1)
			month = "0" + month;
		if (day.length() == 1)
			day = "0" + day;
		if (hour.length() == 1)
			hour = "0" + hour;
		if (minutes.length() == 1)
			minutes = "0" + minutes;
		if (second.length() == 1)
			second = "0" + second;
		if (milisecond.length() == 1)
			second = "0" + second;
		sbNumber.append(month);
		sbNumber.append(day);
		sbNumber.append(hour);
		sbNumber.append(minutes);
		sbNumber.append(second);
		sbNumber.append(milisecond);

		return Long.parseLong(sbNumber.toString());
	}

	// for generating Random Password
	public String generateRandomString(int noOfChar) {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < noOfChar; i++) {
			Random randomGenerator = new Random();
			int number = randomGenerator.nextInt(pwdgen.length());
			char ch = pwdgen.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	// ///For set if null value
	public static String setIfNull(Object obj, String nullValue) {
		if (!(null != nullValue && nullValue.trim().length() > 0))
			nullValue = "";
		if (null == obj)
			return nullValue;
		else
			return obj.toString();
	}
	
	public static boolean checkNullAndEmpty(String... var){
		boolean flag = false;
		if(var.length>0){

		for (int i = 0; i < var.length; i++) {
		String string = var[i];
		if(null!=string && string.trim().length()>0){
		flag = true;
		}else{
		flag = false;
		return flag;
		}
		}

		}
		return flag;
		}
}
