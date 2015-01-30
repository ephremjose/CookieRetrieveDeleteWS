package com.edu.drexel.util; 

import java.net.InetAddress;
import java.util.HashMap;





public class Util 
{
	public static InetAddress IPAddress = null;
	static {
		try {
			IPAddress = InetAddress.getLocalHost();
		} catch (Exception e) {
			System.out.println("Exception caught while getting the IPAddress : " + e);
		}
	}

	
	public static String getIPAddress(){
		if (IPAddress != null) {
			return IPAddress.getHostAddress();
		} else {
			return null;
		}
	}
public static boolean isDataParsable(String strVal, String parseType) {
		
		try {
			if(parseType.equals("NUMBER")) {
				Long.parseLong(strVal);
			} else if(parseType.equals("FLOAT")) {
				Float.parseFloat(strVal);
			} else if(parseType.equals("DATE")) {
				Long.parseLong(strVal);
			} 
			return true;
		} catch(Exception e) {
			return false;
		}
	}
} 
