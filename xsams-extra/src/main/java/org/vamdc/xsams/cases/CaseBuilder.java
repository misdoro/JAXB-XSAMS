package org.vamdc.xsams.cases;

import org.vamdc.xsams.cases.common.BaseCase;
import org.vamdc.xsams.util.StateCore;

public class CaseBuilder {
	public static enum Cases{
		dcs,
		hunda,
		hundb,
		ltcs,
		nltcs,
		stcs,
		lpcs,
		asymcs,
		asymos,
		sphcs,
		sphos,
		ltos,
		lpos,
		nltos
	}
	
	public static BaseCase buidCase(StateCore state){
		switch(state.getCaseID()){
		case 1:
			return new CaseDCS(state);
		case 2:
			return new CaseHundA(state);
		case 3:
			return new CaseHundB(state);
		case 4:
			return new CaseLTCS(state);
		case 5:
			return new CaseNLTCS(state);
		case 6:
			return new CaseSTCS(state);
		case 7:
			return new CaseLPCS(state);
		case 8:
			return new CaseAsymCS(state);
		case 9:
			return new CaseAsymOS(state);
		case 10:
			return new CaseSphCS(state);
		case 11:
			return new CaseSphOS(state);
		case 12:
			return new CaseLTOS(state);
		case 13:
			return new CaseLPOS(state);
		case 14:
			return new CaseNLTOS(state);
		default:
			return null;
		}
	}
}
