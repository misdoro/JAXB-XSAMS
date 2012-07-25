package org.vamdc.xsams.cases;

import org.vamdc.xsams.util.QuantumNumber;

public class SymType extends org.vamdc.xsams.cases.common.SymType {

	public SymType(QuantumNumber qn) {
		super();
		this.setGroup(qn.getRefSpin());
		this.setName(qn.getLabel());
		this.setValue(qn.getStringValue());
		
	}

	
}
