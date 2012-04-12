package org.vamdc.xsams.cases;

import org.vamdc.xsams.util.QuantumNumber;

public class VibrationalQNType 
extends org.vamdc.xsams.cases.common.VibrationalQNType{
	
	public VibrationalQNType(QuantumNumber qn){
		super();
		if (qn!=null){
			this.setMode(qn.getModeIndex());
			this.setValue(qn.getIntValue());
		}
	}

}
