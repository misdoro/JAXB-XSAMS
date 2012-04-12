package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.hunda.QNs;
import org.vamdc.xsams.util.StateCore;
import org.vamdc.xsams.util.QuantumNumber.QNType;

public class CaseHundA extends org.vamdc.xsams.cases.hunda.Case{

	public CaseHundA(StateCore state){
		super();
		//Set ID
		this.caseID="hunda";
		
		//Attach quantum numbers
		this.qNs = new QNs();
		
		//Fill in quantum numbers
		//Electronic state label
		qNs.setElecStateLabel(state.getElecState());
		//Electronic symmetries
		qNs.setElecInv(state.getQNumStrValueByType(QNType.elecInv));
		qNs.setElecRefl(state.getQNumStrValueByType(QNType.elecRefl));
		//Lambda
		qNs.setLambda(state.getQNumIntValueByType(QNType.Lambda));
		//Sigma
		qNs.setSigma(state.getQNumValueByType(QNType.Sigma));
		//Omega
		qNs.setOmega(state.getQNumValueByType(QNType.Omega));
		//S
		qNs.setS(state.getQNumValueByType(QNType.S));
		//v
		qNs.setV(state.getQNumIntValueByType(QNType.V));
		//J
		qNs.setJ(state.getQNumValueByType(QNType.J));
		//F1
		if (state.checkQNum(QNType.Fi))
			qNs.setF1(new NuclearSpinAMType(		
					state.getQNumByType(QNType.Fi)));
		//F
		if (state.checkQNum(QNType.F))
			qNs.setF(new NuclearSpinAMType(
					state.getQNumByType(QNType.F)));
		//R
		if (state.checkQNum(QNType.R))
			qNs.setR(
					new RankingType(
							state.getQNumByType(QNType.R)));
		//parity
		qNs.setParity(state.getQNumStrValueByType(QNType.Parity));
		//kronigParity
		qNs.setKronigParity(state.getQNumStrValueByType(QNType.kParity));
		//asSym
		qNs.setAsSym(state.getQNumStrValueByType(QNType.AsSym));
		
	}
	
}
