package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.ltos.QNs;
import org.vamdc.xsams.util.QuantumNumber;
import org.vamdc.xsams.util.StateCore;
import org.vamdc.xsams.util.QuantumNumber.QNType;

public class CaseLTOS 
extends org.vamdc.xsams.cases.ltos.Case{

	public CaseLTOS(StateCore state){
		super();
		//Set ID
		this.caseID="ltos";

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
			switch (qn.getModeIndex()){
			case 1:
				qNs.setV1(qn.getIntValue());
				break;
			case 2:
				qNs.setV2(qn.getIntValue());
				break;
			case 3:
				qNs.setV3(qn.getIntValue());
				break;
			default:
				break;
			}
		}

		//l2 vibrational angular momentum
		qNs.setL2(state.getQNumIntValueByType(QNType.li));
		//J
		qNs.setJ(state.getQNumIntValueByType(QNType.J));

		//intermediate angular momentum Fi
		for (QuantumNumber qn: state.getQNumsByType(QNType.Fi)){
			switch (qn.getModeIndex()){
			case 1:
				qNs.setF1(new NuclearSpinAMType(qn));
				break;
			case 2:
				qNs.setF2(new NuclearSpinAMType(qn));
				break;
			default:
				break;
			}
		}

		//intermediate angular momentum F
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
