package org.vamdc.xsams.util;

/**
 * Settings container
 * Keeps ID's prefix and output limits
 * @author doronin
 *  
 */
public enum XSAMSSettings {
	statesLimit(-1),
	processesLimit(-1),
	idPrefix("DBNAME"),
	prettyprint(1),
	httpDataTimeout(60000),
	httpConnectTimeout(2000),
	;
	
	private String defStrval="";
	private Integer defIntval=0;
	private String strval;
	private Integer intval;
	
	XSAMSSettings(String value){
		this.defStrval = value;
		this.strval=null;
	}
	
	XSAMSSettings(Integer value){
		this.defIntval = value;
		this.intval = 0;
	}
	
	public void useDefault(){
		this.strval=null;
		this.intval=null;
	}
	
	public String getStrValue(){
		if (this.strval!=null)
			return this.strval;
		return this.defStrval;
	}
	
	public Integer getIntValue(){
		if (this.intval!=null)
			return this.intval;
		return this.defIntval;
	}
	
	public void setStrValue(String value){
		this.strval=value;
	}
	
	public void setIntValue(Integer value){
		this.intval=value;
	}
}
