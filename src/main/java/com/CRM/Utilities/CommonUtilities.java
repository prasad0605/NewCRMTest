package com.CRM.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtilities {
	
	public static String getTimestamp() {
		Date d=new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");  
	    String strDate= formatter.format(d);  
	    System.out.println(strDate);  
		return strDate;
	}

}
