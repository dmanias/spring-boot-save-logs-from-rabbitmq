package com.jtampakakis.bluemaster.savelogs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private static int idMin=1;
	private static int idMax=11;
	public static String numRegex="[0-9]+";
	
	private static int nameMin=3;
	private static int nameMax=20;
	public static String nameRegex="[a-zA-Z0-9_-]+";
	
	private static int ipMin=7;
	private static int ipMax=15;
	public static String ipRegex="^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
	public static String macRegex="^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
	
	public static String emailRegex = "^(.+)@(.+)$";
	public static String domainRegex="^(((?!-))(xn--|_)?[a-z0-9-]{0,61}[a-z0-9]{1,1}\\.)*(xn--)?([a-z0-9][a-z0-9\\-]{0,60}|[a-z0-9-]{1,30}\\.[a-z]{2,})$";
	
	private static int passMin=4;
	private static int passMax=100;
	public static String passRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	public static String realNameRegex="[a-zA-Z0-9 ]+";
	
	public static String productRegex="[a-zA-Z0-9-_()& ]+";
	private static int productMin=5;
	private static int productMax=15;
	
	public static String textRegex="[a-zA-Z0-9 ]+";
	private static int textMin=1;
	private static int textMax=255;
	
	public static String uuidRegex="^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
	
	

	public static void validate (String fieldName, String value, int minChars, int maxChars, String regex, boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (minChars!=0 && value.length()<minChars) throw new ValidationException(fieldName+" should be more than "+minChars+" chars");
		if (maxChars!=0 && value.length()>maxChars) throw new ValidationException(fieldName+" should be less than "+maxChars+" chars");
		if (regex!=null && !value.matches(regex)) throw new ValidationException(fieldName+ " includes invalid characters");	
	}
	
	public static void IdValidator(String fieldName,String value, boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<idMin || value.length()>idMax) throw new ValidationException("Invalid "+fieldName+" length - "+value);
		if (!value.matches(numRegex)) throw new ValidationException("Invalid "+fieldName+" value - "+value);
		try {
			Integer.parseInt(value);
		}catch (Exception e) {
			throw new ValidationException("Invalid "+fieldName+" value - "+value);
		}
	}
	
	public static void NameValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<nameMin || value.length()>nameMax) throw new ValidationException("Invalid "+fieldName+" length");
		if (!value.matches(nameRegex)) throw new ValidationException(fieldName+" contains invalid characters - "+value);
	}
	
	public static void NetworkNameValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<2 || value.length()>8) throw new ValidationException("Invalid "+fieldName+" length");
		if (!value.matches(nameRegex)) throw new ValidationException(fieldName+" contains invalid characters - "+value);
	}
	
	public static void NumberValidator(String fieldName,String value,int minVal,int maxVal,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<idMin || value.length()>idMax) throw new ValidationException("Invalid "+fieldName+" length");
		if (!value.matches(numRegex)) throw new ValidationException("Invalid "+fieldName+" value - "+value);
		try {
			int intval = Integer.parseInt(value);
			if (intval<minVal || intval>maxVal) throw new ValidationException(fieldName+ "should be between "+minVal+" and "+maxVal+" - Current: "+value);
		}catch (Exception e) {
			throw new ValidationException("Invalid "+fieldName+" value");
		}
	}
	
	public static void DoubleValidator(String fieldName,String value,double minVal,double maxVal,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		try {
			double dval = Double.parseDouble(value);
			if (dval<minVal || dval>maxVal) throw new ValidationException(fieldName+ "should be between "+minVal+" and "+maxVal);
		}catch (Exception e) {
			throw new ValidationException("Invalid "+fieldName+" value");
		}
	}
	
	public static void ProductValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<productMin || value.length()>productMax) throw new ValidationException("Invalid "+fieldName+" length");
		if (!value.matches(productRegex)) throw new ValidationException(fieldName+" contains invalid characters");
	}
	
	public static void FixedValsValidator(String fieldName,String value,String [] allowed,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		for (int i=0;i<allowed.length;i++) {
			if (value.equals(allowed[i])) return;
		}
		throw new ValidationException(fieldName+" does not belong to the allowed values");
	}
	
	public static void IPValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<ipMin || value.length()>ipMax) throw new ValidationException("Invalid "+fieldName+" length");
		if (!value.matches(ipRegex)) throw new ValidationException(fieldName+" is invalid");
	}
	
	public static void MACValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		Pattern p = Pattern.compile(macRegex);
        Matcher m = p.matcher(value);
        if (!m.find()) throw new ValidationException(fieldName+" has an invalid format");
	}
	
	public static void DateValidator(String fieldName, String value, String dateFromat, boolean allowNull){
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			//if not valid, it will throw ParseException
			Date date = sdf.parse(value);
			System.out.println(date);
		} catch (ParseException e) {
			throw new ValidationException(fieldName+" has an invalid format");
		}
	}
	
	public static void VoucherValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (!value.matches(numRegex)) throw new ValidationException("Invalid "+fieldName+" value");
		if (value.length()<8 || value.length()>14) throw new ValidationException("Invalid "+fieldName+" length");
	}
	
	public static void EmailValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
        if (!value.matches(emailRegex)) throw new ValidationException(fieldName+" has an invalid format");
	}
	
	public static void PasswordValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<passMin || value.length()>passMax) throw new ValidationException("Invalid "+fieldName+" length");
		if (!value.matches(passRegex)) throw new ValidationException(fieldName+" contains invalid characters");
	}
	
	public static void RealNameValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<nameMin || value.length()>nameMax) throw new ValidationException("Invalid "+fieldName+" length");
		if (!value.matches(realNameRegex)) throw new ValidationException(realNameRegex+" contains invalid characters");
	}
	
	public static void TextValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (value.length()<textMin || value.length()>textMax) throw new ValidationException("Invalid "+fieldName+" length");
		if (!value.matches(textRegex)) throw new ValidationException(fieldName+" contains invalid characters");
	}
	
	public static void UUIDValidator(String fieldName,String value,boolean allowNull) {
		if (allowNull && value==null) return;
		if (!allowNull && value==null) throw new ValidationException(fieldName+" is required");
		if (!value.matches(uuidRegex)) throw new ValidationException(fieldName+" is invalid UUID");
	}
	
	public static void main (String [] args) {
		//UUIDValidator("etst","e3f4291d-9e6c-4418-9b6f-642cbe8ac34f",false);
		if ("http://in.gr".matches(domainRegex)) System.out.println("Match!");
		else System.out.println("No Match!");
	}
}
