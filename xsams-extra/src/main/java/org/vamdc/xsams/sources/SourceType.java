package org.vamdc.xsams.sources;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class SourceType extends org.vamdc.xsams.schema.SourceType{
	public SourceType(){
		super();
	}
	
	public void setYear(int year){
		XMLGregorianCalendar cal = null;
		try{
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			cal.setYear(year);
			cal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		}catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		this.setYear(cal);
	}
}
