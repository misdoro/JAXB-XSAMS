package org.vamdc.xsams.util;

/**
 * Quantum number description, is used by caseBuilders.
 * @author Misha Doronin
 *
 */
public class QuantumNumber {
	private Double value;
	private String stringValue;
	private int ID;
	private String Label;
	/**
	 * All possible case quantum number types
	 */
	public enum QNType{
		J,		//Total angular momentum w/o nuclear spin
		V,		//Vibrational qnum (implies mode index)
		Fi,		//Hyperfine intermediate angular momentum, implies ref to nuclear spin and mode index 
		F,		//Hyperfine total angular momentum, implies ref to nuclear spin
		I,		//Hyperfine total nuclear spin angular momentum
		R,		//Generic quantum number, implies label
		Parity,	//Parity of total wavefunction, 1 or -1 for value, +or- as output
		AsSym, 	//Total rovib wavefunction symmetry, 0 or 1 for 's' or 'a' as output respectively
		L,		//Atoms: Total orbital angular momentum, molecules: vibrational angular momentum
		S,		//Total spin ang momentum
		Sigma,	//S projection magnitude
		Omega,	//Projection of J
		Lambda,	//Projection of electronic momentum
		kParity,//kronig parity, 0 for e, 1 for f
		N,		//Total angular momentum w/o electronic and nuclear spin
		spinLbl,//Spin component label for hundb
		li,		//Degenerate bending vibration, implies mode index
		Ka,		//Rot qnum of symm top
		Kc,		//Rot qnum of symm top
		K,		//Ang momentum projection on symmetry axis
		vibInv,	//Vibrational parity, 0 or 1 for 's' or 'a' as output respectively
		vibRefl, //Vibrational wavefunction parity, 1 or -1 for '+' or '-' as output respectively
		vibSym,	//Vibrational symmetry specie, group attribute goes to label
		
		rotSym,	//Rotational symmetry specie, group attribute goes to label
		roVibSym, //Rovibration symmetry specie, group attribute goes to label
		//estate,	//Electronic state label, value from 'label'
		elecSym,	//Electronic wavefunction symmetry
		elecInv,	//Electronic parity, 'g' for 1, 'u' for others
		elecRefl,	//Electronic parity, '+' for 1, '-' for others
		genQN,	//Generic qn
		genSym,	//and generic symmetry
	};
	
	/**
	 * Mode index for vibrational types
	 */
	private int ModeIndex;
	
	private QNType Type;
	private String refSpin;
	
	
	/**
	 * @return quantum number type
	 */
	public QuantumNumber.QNType getType(){
		return Type;
	}

	/**
	 * @return double representation of quantum number
	 */
	public Double getValue() {
		return value;
	}
	
	/**
	 * @return integer value for quantum numbers that expect integers
	 */
	public int getIntValue(){
		return (int) Math.round(value);
	}
	
	/**
	 * Returns stored string value or string representation of stored numerical value
	 * For symmetry quantum numbers, returns '+','s','e' for 1, '-','a','f' for any other value
	 * for parity, (assym,vibinv), kparity correspondingly
	 * @return stored string value or string representation of stored numerical value
	 */
	public String getStringValue() {
		if (stringValue!=null)
			return stringValue;
		switch(Type){
		case vibRefl:
		case elecRefl:
		case Parity:
			if (getIntValue()==1)
				return "+";
			else return "-";
		case vibInv:
		case AsSym:
			if (getIntValue()==1)
				return "a";
			else return "s";
		case kParity:
			if (getIntValue()==1)
				return "e";
			else return "f";
		case elecInv:
			if (getIntValue()==1)
				return "g";
			else return "u";
		default:
			return value.toString();
		}
	}

	/**
	 * Set numeric quantum number value
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	
	
	/**
	 * Set arbitrary string value for quantum number.
	 * If not set, getStringValue will return string representation of stored numeric value.
	 * @param stringValue
	 */
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return Label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		Label = label;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(QNType type) {
		Type = type;
	}

	public void setRefSpin(String refSpin) {
		this.refSpin = refSpin;
	}

	public String getRefSpin() {
		return refSpin;
	}

	public void setModeIndex(int modeIndex) {
		ModeIndex = modeIndex;
	}

	public int getModeIndex() {
		return ModeIndex;
	}
	
}
