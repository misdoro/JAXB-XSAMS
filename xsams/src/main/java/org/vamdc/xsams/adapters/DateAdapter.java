package org.vamdc.xsams.adapters;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter class for xs:date
 * @author doronin
 */
public class DateAdapter extends XmlAdapter<String, Date>{
	
	public Date unmarshal(String s) {
		return DatatypeConverter.parseDate(s).getTime();
	}
	
	public String marshal(Date dt) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return DatatypeConverter.printDate(cal);
	}

}
