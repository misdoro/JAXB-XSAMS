package org.vamdc.xsams.cases;

import org.vamdc.xsams.util.QuantumNumber;

public class RankingType 
extends org.vamdc.xsams.cases.common.RankingType{

	public RankingType(QuantumNumber qn){
		super();
		if (qn!=null){
			this.setValue(qn.getIntValue());
			this.setName(qn.getLabel());
		}
	}
	
}
