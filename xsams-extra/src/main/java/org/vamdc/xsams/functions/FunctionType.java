package org.vamdc.xsams.functions;

import org.vamdc.xsams.common.ArgumentType;

/**
Autogenerated customizeable class for user's handy methods
*/
public class FunctionType extends org.vamdc.xsams.schema.FunctionType {
	
	public FunctionType(){
		super();
	}

	public FunctionType addParameter(String name, String units, String description){
		if (this.getParameters()==null)
			this.setParameters(new FunctionParametersType());
		this.getParameters().getParameters().add(
				new FunctionParameterType(name,units,description));
		return this;
	}
	
	public FunctionType addArgument(String name, String units, String description){
		if (this.getArguments()==null)
			this.setArguments(new ArgumentsType());
		this.getArguments().getArguments().add(new ArgumentType(name,units,description));
		return this;
	}
	
	public org.vamdc.xsams.schema.ArgumentType getArgument(){
		if (this.getArguments()!=null && this.arguments.getArguments().size()>0)
			return this.arguments.getArguments().get(0);
		return null;
	}
	
}

