package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.lpos.QNs;
import org.vamdc.xsams.util.QuantumNumber;
import org.vamdc.xsams.util.StateCore;
import org.vamdc.xsams.util.QuantumNumber.QNType;

public class CaseLPOS 
extends org.vamdc.xsams.cases.lpos.Case{

	public CaseLPOS(StateCore state){
		super();
		//Set ID
		this.caseID="lpos";

		//Attach quantum numbers
		this.qNs = new QNs();

		//Fill in quantum numbers
		//Electronic state label
		qNs.setElecStateLabel(state.getElecState());
		//Electronic symmetries
		qNs.setElecInv(state.getQNumStrValueByType(QNType.elecInv));
		qNs.setElecRefl(state.getQNumStrValueByType(QNType.elecRefl));
		//S
		qNs.setS(state.getQNumValueByType(QNType.S));
		//N
		qNs.setN(state.getQNumIntValueByType(QNType.N));
		//Vibration
		for (QuantumNumber qn: state.getQNumsByType(QNType.V)){
			qNs.getVis().add(new VibrationalQNType(qn));

		}

		//li
		for (QuantumNumber qn: state.getQNumsByType(QNType.li)){
			qNs.getLis().add(new VibrationalAMQNType(qn));
		}

		//l
		qNs.setL(state.getQNumIntValueByType(QNType.L));

		//vibInv
		qNs.setVibInv(state.getQNumStrValueByType(QNType.vibInv));

		//vibRefl
		qNs.setVibRefl(state.getQNumStrValueByType(QNType.vibRefl));

		//J
		qNs.setJ(state.getQNumIntValueByType(QNType.J));

		//I
		if (state.checkQNum(QNType.I))
			qNs.setI(new NuclearSpinAMType(
					state.getQNumByType(QNType.I)));

		//intermediate angular momentum Fi
		for (QuantumNumber qn: state.getQNumsByType(QNType.Fi)){
			qNs.getFjs().add(new NuclearSpinIntermediateAMType(qn));
			
		}

		//intermediate angular momentum F
		//F
		if (state.checkQNum(QNType.F))
			qNs.setF(new NuclearSpinAMType(
					state.getQNumByType(QNType.F)));
		
		//R
		for (QuantumNumber qn: state.getQNumsByType(QNType.R)){
			qNs.getRS().add(new RankingType(qn));
		}
		
		//parity
		qNs.setParity(state.getQNumStrValueByType(QNType.Parity));
		//kronigParity
		qNs.setKronigParity(state.getQNumStrValueByType(QNType.kParity));
		//asSym
		qNs.setAsSym(state.getQNumStrValueByType(QNType.AsSym));

	}
}
