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
	;
	
	private Integer defIntval=0;
	private Integer intval;
	
	IOSettings(Integer value){
		this.defIntval = value;
		this.intval = 0;
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
}
