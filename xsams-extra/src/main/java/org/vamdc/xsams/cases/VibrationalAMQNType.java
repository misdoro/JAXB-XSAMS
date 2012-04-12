package org.vamdc.xsams.cases;

import org.vamdc.xsams.util.QuantumNumber;

public class VibrationalAMQNType extends
		org.vamdc.xsams.cases.common.VibrationalAMQNType {

	public VibrationalAMQNType(QuantumNumber qn){
		super();
		if (qn!=null){
			this.setValue(qn.getIntValue());
			this.setMode(qn.getModeIndex());
		}
	}
}
