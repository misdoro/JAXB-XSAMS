package org.vamdc.xsams.cases;

import org.vamdc.xsams.util.QuantumNumber;

public class SymmetrySpeciesType 
extends org.vamdc.xsams.cases.common.SymmetrySpeciesType{
	public SymmetrySpeciesType(QuantumNumber qn){
		super();
		if (qn!=null){
			this.setValue(qn.getStringValue());
			this.setGroup(qn.getLabel());
		}
	}

}
