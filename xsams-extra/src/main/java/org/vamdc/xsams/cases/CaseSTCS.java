package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.stcs.QNs;
import org.vamdc.xsams.util.QuantumNumber;
import org.vamdc.xsams.util.StateCore;
import org.vamdc.xsams.util.QuantumNumber.QNType;

public class CaseSTCS 
extends org.vamdc.xsams.cases.stcs.Case{

	public CaseSTCS(StateCore state){
		super();
		//Set ID
		this.caseID="stcs";

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
		for (QuantumNumber qn: state.getQNumsByType(QNType.L)){
			qNs.getLS().add(qn.getIntValue());
		}

		//li
		for (QuantumNumber qn: state.getQNumsByType(QNType.li)){
			qNs.getLis().add(new VibrationalAMQNType(qn));
		}

		//vibInv
		qNs.setVibInv(state.getQNumStrValueByType(QNType.vibInv));

		//vibSym
		if (state.checkQNum(QNType.vibSym))
			qNs.setVibSym(new  SymmetrySpeciesType(
					state.getQNumByType(QNType.vibSym)));

		//J
		qNs.setJ(state.getQNumIntValueByType(QNType.J));

		//intermediate angular momentum Fi
		for (QuantumNumber qn: state.getQNumsByType(QNType.Fi)){
			qNs.getFjs().add(new NuclearSpinIntermediateAMType(qn));

		}

		//K
		qNs.setK(state.getQNumIntValueByType(QNType.K));

		//Rotational symmetry
		if (state.checkQNum(QNType.rotSym))
			qNs.setRotSym(new SymmetrySpeciesType(
					state.getQNumByType(QNType.rotSym)));

		//Rovib symmetry
		if (state.checkQNum(QNType.roVibSym))
			qNs.setRovibSym(new SymmetrySpeciesType(
					state.getQNumByType(QNType.roVibSym)));
		
		//I
		if (state.checkQNum(QNType.I))
			qNs.setI(new NuclearSpinAMType(
					state.getQNumByType(QNType.I)));

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


	}
}
