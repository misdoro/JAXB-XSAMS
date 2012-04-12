package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.asymos.QNs;
import org.vamdc.xsams.util.QuantumNumber;
import org.vamdc.xsams.util.StateCore;
import org.vamdc.xsams.util.QuantumNumber.QNType;

public class CaseAsymOS 
extends org.vamdc.xsams.cases.asymos.Case{

	public CaseAsymOS(StateCore state){
		super();
		//Set ID
		this.caseID="asymos";

		//Attach quantum numbers
		this.qNs = new QNs();

		//Fill in quantum numbers
		//Electronic state label
		qNs.setElecStateLabel(state.getElecState());
		//Electronic symmetries
		if (state.checkQNum(QNType.elecSym))
			qNs.setElecSym(new  SymmetrySpeciesType(
					state.getQNumByType(QNType.elecSym)));
		
		qNs.setElecInv(state.getQNumStrValueByType(QNType.elecInv));
		
		//S
		qNs.setS(state.getQNumValueByType(QNType.S));
		
		//Vibration
		for (QuantumNumber qn: state.getQNumsByType(QNType.V)){
			qNs.getVis().add(new VibrationalQNType(qn));

		}

		//vibInv
		qNs.setVibInv(state.getQNumStrValueByType(QNType.vibInv));

		//vibSym
		if (state.checkQNum(QNType.vibSym))
			qNs.setVibSym(new  SymmetrySpeciesType(
					state.getQNumByType(QNType.vibSym)));

		//J
		qNs.setJ(state.getQNumValueByType(QNType.J));
		
		//N
		qNs.setN(state.getQNumIntValueByType(QNType.N));
		
		
		//Ka
		qNs.setKa(state.getQNumIntValueByType(QNType.Ka));
		//Kc
		qNs.setKc(state.getQNumIntValueByType(QNType.Kc));

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


	}
}
