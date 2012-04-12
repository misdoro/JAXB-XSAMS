package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.hundb.QNs;
import org.vamdc.xsams.util.StateCore;
import org.vamdc.xsams.util.QuantumNumber.QNType;

public class CaseHundB extends org.vamdc.xsams.cases.hundb.Case{

	public CaseHundB(StateCore state){
		super();
		//Set ID
		this.caseID="hundb";
		
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
		//S
		qNs.setS(state.getQNumValueByType(QNType.S));
		//v
		qNs.setV(state.getQNumIntValueByType(QNType.V));
		//J
		qNs.setJ(state.getQNumValueByType(QNType.J));
		//N
		qNs.setN(state.getQNumIntValueByType(QNType.N));
		//SpinCompLabel
		qNs.setSpinComponentLabel(state.getQNumIntValueByType(QNType.spinLbl));
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
