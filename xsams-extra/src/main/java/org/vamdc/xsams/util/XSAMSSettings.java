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
		this.intval = value;
	}
	
	public void useDefault(){
		this.strval=this.strval;
		this.intval=this.defIntval;
	}
	
	public String getStrValue(){
		return this.strval;
	}
	
	public Integer getIntValue(){
		return this.intval;
	}
	
	public void setStrValue(String value){
		this.strval=value;
	}
	
	public void setIntValue(Integer value){
		this.intval=value;
	}
}
