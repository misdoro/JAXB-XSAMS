package org.vamdc.xsams.cases;

import org.vamdc.xsams.util.QuantumNumber;

public class NuclearSpinIntermediateAMType 
	extends org.vamdc.xsams.cases.common.NuclearSpinIntermediateAMType{

	public NuclearSpinIntermediateAMType(QuantumNumber qn){
		super();
		if (qn!=null){
			this.setValue(qn.getValue());
			this.setJ(qn.getModeIndex());
			this.setNuclearSpinRef(qn.getRefSpin());
		}
	}
}
