package org.vamdc.xsams.io;

/**
 * Settings container
 * @author doronin
 *  
 */
public enum IOSettings {
	prettyprint(1),//Pretty-print output documents or not
	compress(1),//Try GZIP compression of transfer
	httpDataTimeout(60000),//Data timeout for HTTP
	httpConnectTimeout(2000),//Connect timeout for HTTP
	userAgent("Java VAMDC client//12.07"),//User agent
	//TODO: update version here
	;
	
	private Integer defIntval=0;
	private Integer intval;
	private String strVal;
	
	IOSettings(Integer value){
		this.defIntval = value;
		this.intval = 0;
	}
	IOSettings(String value){
		this.strVal=value;
	}
	
	public void useDefault(){
		this.intval=null;
	}
	
	public Integer getIntValue(){
		if (this.intval!=null)
			return this.intval;
		return this.defIntval;
	}
	public void setIntValue(Integer value){
		this.intval=value;
	}
	
	public void setStrVal(String value){
		this.strVal = value;
	}
	public String getStrVal(){
		return this.strVal;
	}
}
