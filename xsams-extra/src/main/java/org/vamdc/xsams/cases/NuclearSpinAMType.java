package org.vamdc.xsams.cases;

import org.vamdc.xsams.util.QuantumNumber;

public class NuclearSpinAMType extends org.vamdc.xsams.cases.common.NuclearSpinAMType{
	public NuclearSpinAMType(double value, String spinref){
		super();
		this.setValue(value);
		this.setNuclearSpinRef(spinref);
		
	}

	public NuclearSpinAMType(QuantumNumber qn) {
		super();
		if (qn!=null){
			this.setValue(qn.getValue());
			this.setNuclearSpinRef(qn.getRefSpin());
		}
	}

}
