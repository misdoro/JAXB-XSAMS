package org.vamdc.xsams.cases;

import org.vamdc.xsams.util.QuantumNumber;

public class CoupledNuclearSpinAMType extends org.vamdc.xsams.cases.common.CoupledNuclearSpinAMType{

	public CoupledNuclearSpinAMType(QuantumNumber qn) {
		super();
		if (qn!=null){
			this.setValue(qn.getValue());
			this.setNuclearSpinRef(qn.getRefSpin());
		}
	}

}
