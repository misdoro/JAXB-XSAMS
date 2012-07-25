package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.dcs.QNs;
import org.vamdc.xsams.util.QuantumNumber;
import org.vamdc.xsams.util.StateCore;
import org.vamdc.xsams.util.QuantumNumber.QNType;

public class CaseDCS extends org.vamdc.xsams.cases.dcs.Case{

	public CaseDCS(StateCore state){
		super();
		//Set ID
		this.caseID="dcs";
		
		//Attach quantum numbers
		this.qNs = new QNs();
		//Electronic state label
		qNs.setElecStateLabel(state.getElecState());
		//Vibrational qnum
		qNs.setV(state.getQNumIntValueByType(QNType.V));
		//Total angular momentum
		qNs.setJ(state.getQNumIntValueByType(QNType.J));
		//Intermediate angular momentum
		if (state.checkQNum(QNType.Fi))
			qNs.setF1(
					new NuclearSpinAMType(
							state.getQNumByType(QNType.Fi)));
		
		//I
		if (state.checkQNum(QNType.I))
			qNs.setI(new CoupledNuclearSpinAMType(
					state.getQNumByType(QNType.I)));
		
		//Total ang. mom. incl. nucl. spin
		if (state.checkQNum(QNType.F)){
			qNs.setF(
					new NuclearSpinAMType(
							state.getQNumByType(QNType.F)));
		}
		if (state.checkQNum(QNType.I))
			qNs.setI(new CoupledNuclearSpinAMType(
					state.getQNumByType(QNType.I)));
		
		//R
		for (QuantumNumber qn: state.getQNumsByType(QNType.R)){
			qNs.getRS().add(new RankingType(qn));
		}
		
		qNs.setParity(state.getQNumStrValueByType(QNType.Parity));
		//kronigParity
		qNs.setKronigParity(state.getQNumStrValueByType(QNType.kParity));
				
		qNs.setAsSym(state.getQNumStrValueByType(QNType.AsSym));
	}

}
