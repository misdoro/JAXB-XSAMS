package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.lpcs.QNs;
import org.vamdc.xsams.util.QuantumNumber;
import org.vamdc.xsams.util.StateCore;
import org.vamdc.xsams.util.QuantumNumber.QNType;

public class CaseLPCS 
extends org.vamdc.xsams.cases.lpcs.Case{

	public CaseLPCS(StateCore state){
		super();
		//Set ID
		this.caseID="lpcs";

		//Attach quantum numbers
		this.qNs = new QNs();

		//Fill in quantum numbers
		//Electronic state label
		qNs.setElecStateLabel(state.getElecState());

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

		//vibSym
		qNs.setVibSym(new SymmetrySpeciesType(state.getQNumByType(QNType.vibSym)));
		
		//J
		qNs.setJ(state.getQNumIntValueByType(QNType.J));

		//I
		for (QuantumNumber qn: state.getQNumsByType(QNType.I)){
			qNs.getIS().add(new CoupledNuclearSpinAMType(qn));
		}

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
