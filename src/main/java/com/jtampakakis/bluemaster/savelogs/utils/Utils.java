package com.jtampakakis.bluemaster.savelogs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {
	
	public static String SystemCommand(String command, Logger LOGGER) {
		String s = null;
		String response="";
		try {
			LOGGER.info("Executing-> "+command);
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			while ((s = stdInput.readLine()) != null) {
				LOGGER.config(s);
				response+=s;
			}

			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) {
				LOGGER.warning(s);
				response+="ERROR "+s;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static String SystemCommand(String [] command, Logger LOGGER) {
		String s = null;
		String response="";
		try {
			LOGGER.info("Executing-> "+command);
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			while ((s = stdInput.readLine()) != null) {
				LOGGER.config(s);
				response+=s;
			}

			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) {
				LOGGER.warning(s);
				response+="ERROR "+s;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static String [] ListFiles(String filepath, String prefixStart, String prefixEnd, String prefixContains) {

		// Creates an array in which we will store the names of files and directories
		String[] pathnames;

		// Creates a new File instance by converting the given pathname string
		// into an abstract pathname
		File f = new File(filepath);
		
		if (prefixStart!=null) {
			FilenameFilter filter = new FilenameFilter() {
		        @Override
		        public boolean accept(File f, String name) {
		            return name.startsWith(prefixStart);
		        }
		    };
		    pathnames = f.list(filter);
		}else if (prefixEnd!=null) {
			FilenameFilter filter = new FilenameFilter() {
		        @Override
		        public boolean accept(File f, String name) {
		            return name.startsWith(prefixEnd);
		        }
		    };
		    pathnames = f.list(filter);
		}else if (prefixContains!=null) {
			FilenameFilter filter = new FilenameFilter() {
		        @Override
		        public boolean accept(File f, String name) {
		            return name.startsWith(prefixContains);
		        }
		    };
		    pathnames = f.list(filter);
		}else pathnames = f.list();

		// For each pathname in the pathnames array
		for (String pathname : pathnames) {
			// Print the names of files and directories
			System.out.println(pathname);
		}
		
		return pathnames;
	}
	
	public static Long IPtoLong(String ip) {
		String [] octets = ip.split("\\.");
		String ipStr="";
		
		for (int i=0;i<octets.length;i++) {
			String octetStr =octets[i];
			while (octetStr.length()<3) {
				octetStr= "0"+octetStr;
			}
			ipStr+=octetStr;
		}
		return Long.parseLong(ipStr);
	}
	
	public static String NetmaskToSubnet(final String netmask){
	    SubnetInfo sub1 = new SubnetUtils("1.1.1.1", netmask).getInfo();
	    String subnet = sub1.getCidrSignature().split("/")[1];
	    return subnet;
	}
	
	public static boolean NetworkOverlapCheck(final String net1, final String net2){
	    SubnetInfo subnet1 = new SubnetUtils(net1).getInfo();
	    SubnetInfo subnet2 = new SubnetUtils(net2).getInfo();

	    int mask1 = subnet1.asInteger(subnet1.getNetmask());
	    int mask2 = subnet2.asInteger(subnet2.getNetmask());

	    int maskToUse = mask1 < mask2 ? mask1 : mask2;
	    int addr1 = subnet1.asInteger(subnet1.getAddress()) & maskToUse;
	    int addr2 = subnet2.asInteger(subnet2.getAddress()) & maskToUse;

	    return addr1 == addr2;
	}
	
	public static String RetrieveCurrentTmstamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String now = dateFormat.format(cal.getTime());
		return now;
	}
	
	public static String SubstractSecondsFromDate(String strdate, int secs) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.SECOND, (cal.get(Calendar.SECOND) - secs));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String substractedDate = dateFormat.format(cal.getTime());
			return substractedDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String AddSecsToDate(String date,int secs) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = dateFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.SECOND, secs);
			return dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}  
    }
	
	public static String RemoveMonthsFromDate(String date, int months) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = dateFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.MONTH, months);
			return dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public static String FindBiggerDate(String datea,String dateb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = sdf.parse(datea);
			Date date2 = sdf.parse(dateb);
			if (date1.compareTo(date2) > 0) {
	            return datea;
	        } else if (date1.compareTo(date2) < 0) {
	            return dateb;
	        } else {
	            return "Equal";
	        } 
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public static String FindSmallerDate(String datea,String dateb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = sdf.parse(datea);
			Date date2 = sdf.parse(dateb);
			if (date1.compareTo(date2) > 0) {
	            return dateb;
	        } else if (date1.compareTo(date2) < 0) {
	            return datea;
	        } else {
	            return datea;
	        } 
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public static String getFirstOfMonth() {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		String first = ft.format(cal.getTime());
		return first;
	}
	
	public static String getFirstOfWeek() {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK,cal.getFirstDayOfWeek());
		String first = ft.format(cal.getTime());
		return first;
	}
	
	public static String PasswordEncode(String password,int length) {
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(length); // Strength set as 16
	     String encodedPassword = encoder.encode(password);
	     System.out.println("BCryptPasswordEncoder");
	     System.out.println(encodedPassword);
	     System.out.println("\n");
	     return encodedPassword;
		//return "1234";
	}
	
	public static boolean PasswordMatch(String password, String dbPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		return encoder.matches(password, dbPassword);
		//return true;
	}
	
	public static String GenerateRandomPassword(int len){
        // ASCII range - alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }
	
	public static String GenerateRandomStringCode() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcsefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 50) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
	}
	
	public static String CalculateNetworkIP(String ip, String subnet) {
		String [] octets = ip.split("\\.");
		String ipBinary="";
		for (int i=0;i<octets.length;i++) {
			String octetBin =Integer.toBinaryString(Integer.parseInt(octets[i]));
			while (octetBin.length()<8) {
				octetBin= "0"+octetBin;
			}
			ipBinary+=octetBin;
		}
		
		//String ipBinary =Integer.toBinaryString(Integer.parseInt(octets[0]))+Integer.toBinaryString(Integer.parseInt(octets[1]))+Integer.toBinaryString(Integer.parseInt(octets[2]))+Integer.toBinaryString(Integer.parseInt(octets[3]));
		String networkBinary="";

		for (int i=0;i<ipBinary.length();i++) {
			if (i<Integer.parseInt(subnet)) networkBinary += ipBinary.charAt(i);
			else networkBinary+=0;
		}
		int oct1 = Integer.parseInt(networkBinary.substring(0,8),2);
		int oct2 = Integer.parseInt(networkBinary.substring(8,16),2);
		int oct3 = Integer.parseInt(networkBinary.substring(16,24),2);
		int oct4 = Integer.parseInt(networkBinary.substring(24,32),2);
		
		return oct1+"."+oct2+"."+oct3+"."+oct4+"/"+subnet;
	}
	
	public static void main (String [] args) {
		PasswordEncode("6rVWrnEb",8);// portal
		//PasswordEncode("tuYheeXAsbWdSXH3",8);// portal
		//PasswordEncode("vagmmuc4xcY28FTS",8);// devices
		//PasswordEncode("RL7k6bqWD7XTHGY5",8);// orchestrator
		//PasswordEncode("RrPNRP7y3Z9P3Bz6",8);// api gateway
		//PasswordEncode("IxGSue8TTqTBGVAN",8);// licensing
		//PasswordEncode("5DVTa6G7ubqvPQqD",8);// remote gateway
		//PasswordEncode("UY4DSSJhVnu4VEJh",8);// unlicensed
		//PasswordEncode("default",8);// unlicensed
	}

}
