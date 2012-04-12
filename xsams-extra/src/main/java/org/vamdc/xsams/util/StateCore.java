package org.vamdc.xsams.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.apache.commons.collections.map.MultiValueMap;
import org.vamdc.xsams.util.QuantumNumber.QNType;

/**
 * State description core structure and methods. May be used in custom molecule state builders or in generic one. Is used by caseBuilders.
 * @author Misha Doronin
 */
public class StateCore {
	private String comment;
	private String description;
	private String energyUnits;
	private String energyOrigin;
	private String nuclearspinsymmetry;
	private String elecState;
	private Double energy;
	private String id;
	private ArrayList<QuantumNumber> qNums = new ArrayList<QuantumNumber>();
	private MultiValueMap vqNumsByType = new MultiValueMap();
	private int caseID;

	public StateCore(){
		elecState="X";
	}
	
	public void AddQNum(QuantumNumber qnum){
		qNums.add(qnum);
		vqNumsByType.put(qnum.getType(), qnum);
	}

	public String getId(){
		if (this.id==null)		this.buildID();
		return this.id;
	}
	
	public void setID(String statelabel){
		this.id = statelabel;
	}
	
	public void buildID(){
		Random r = new Random();
		String token = Long.toString(Math.abs(r.nextLong()), 36);
		this.id = token;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnergyUnits() {
		return energyUnits;
	}
	
	public void setEnergyUnits(String energyUnits) {
		this.energyUnits = energyUnits;
	}

	public void setEnergyOrigin(String energyOrigin) {
		this.energyOrigin = energyOrigin;
	}

	public String getEnergyOrigin() {
		return energyOrigin;
	}
	
	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public ArrayList<QuantumNumber> getqNums() {
		return qNums;
	}

	public void setQNums(ArrayList<QuantumNumber> qNums) {
		this.qNums = qNums;
	}
	
	@SuppressWarnings("unchecked")
	public QuantumNumber getQNumByType(QuantumNumber.QNType itstype){
		ArrayList<QuantumNumber> qntlist = (ArrayList<QuantumNumber>) vqNumsByType.get(itstype);
		if (qntlist==null) return null;
		return (QuantumNumber)qntlist.get(0);
	}
	
	public Double getQNumValueByType(QNType qntype){
		QuantumNumber myQN = this.getQNumByType(qntype);
		if (myQN==null) return null;
		return myQN.getValue();
	}
	public Integer getQNumIntValueByType(QNType qntype) {
		QuantumNumber myQN = this.getQNumByType(qntype);
		if (myQN==null) return null;
		return myQN.getIntValue();
	}
	public String getQNumStrValueByType(QNType qntype) {
		QuantumNumber myQN = this.getQNumByType(qntype);
		if (myQN==null) return null;
		return myQN.getStringValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<QuantumNumber> getQNumsByType(QuantumNumber.QNType QNType){
		Collection<QuantumNumber> collection = vqNumsByType.getCollection(QNType);
		if (collection != null)
			return collection;
		else return new ArrayList<QuantumNumber>();
	}
		
	public boolean checkQNum(QuantumNumber.QNType QNType){
		return vqNumsByType.containsKey(QNType);
	}

	public void setNuclearspinsymmetry(String nuclearspinsymmetry) {
		this.nuclearspinsymmetry = nuclearspinsymmetry;
	}

	public String getNuclearspinsymmetry() {
		return nuclearspinsymmetry;
	}

	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}

	public int getCaseID() {
		return caseID;
	}

	public void setElecState(String elecState) {
		this.elecState = elecState;
	}

	public String getElecState() {
		return elecState;
	}
	
}
