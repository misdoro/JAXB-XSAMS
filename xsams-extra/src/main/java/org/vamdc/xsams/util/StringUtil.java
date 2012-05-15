package org.vamdc.xsams.util;

public class StringUtil {

	public static String checkNull(String data){
		if (data!=null && data.trim().length()>0)
			return data;
		return null;
	}
}
