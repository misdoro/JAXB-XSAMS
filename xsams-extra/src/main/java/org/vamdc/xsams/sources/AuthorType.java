package org.vamdc.xsams.sources;

public class AuthorType extends org.vamdc.xsams.schema.AuthorType{
	
	public AuthorType(){
		super();
	}
	
	/**
	 * Create author from it's name
	 * @param name value for &lt;name&gt; element
	 */
	public AuthorType(String name){
		super();
		this.setName(name);
	}

}
