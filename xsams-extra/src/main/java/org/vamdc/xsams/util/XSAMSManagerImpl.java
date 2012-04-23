package org.vamdc.xsams.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.vamdc.xsams.environments.Environments;
import org.vamdc.xsams.functions.Functions;
import org.vamdc.xsams.methods.Methods;
import org.vamdc.xsams.schema.AtomType;
import org.vamdc.xsams.schema.AtomicStateType;
import org.vamdc.xsams.schema.CollisionalTransitionType;
import org.vamdc.xsams.schema.Collisions;
import org.vamdc.xsams.schema.EnvironmentType;
import org.vamdc.xsams.schema.FunctionType;
import org.vamdc.xsams.schema.MethodType;
import org.vamdc.xsams.schema.MolecularStateType;
import org.vamdc.xsams.schema.MoleculeType;
import org.vamdc.xsams.schema.NonRadiative;
import org.vamdc.xsams.schema.NonRadiativeTransitionType;
import org.vamdc.xsams.schema.ParticleType;
import org.vamdc.xsams.schema.ProcessesType;
import org.vamdc.xsams.schema.Radiative;
import org.vamdc.xsams.schema.RadiativeTransitionType;
import org.vamdc.xsams.schema.SolidType;
import org.vamdc.xsams.schema.SourceType;
import org.vamdc.xsams.schema.SpeciesStateRefType;
import org.vamdc.xsams.schema.SpeciesType;
import org.vamdc.xsams.sources.Sources;
import org.vamdc.xsams.species.atoms.Atoms;
import org.vamdc.xsams.species.molecules.Molecules;
import org.vamdc.xsams.species.particles.Particles;
import org.vamdc.xsams.species.solids.Solids;

/**
 * Root class for XSAMS
 * @author doronin
 *
 */
public class XSAMSManagerImpl extends org.vamdc.xsams.schema.XSAMSData implements org.vamdc.xsams.XSAMSManager{

	/**
	 * Default constructor	
	 */
	public XSAMSManagerImpl(){
		super();
		this.setSources(new Sources());
		this.setSpecies(new SpeciesType());
	}
	
	
	/**
	 * Environments-related methods
	 * Map envID->Environment 
	 */
	@XmlTransient
	Map<String,EnvironmentType> environments = new HashMap<String,EnvironmentType>();
	
	@Override
	public String addEnvironment(EnvironmentType env) {
		if (env==null) 
			return null;
		String envID = env.getEnvID();
		if (envID!=null && !environments.containsKey(envID)){
			environments.put(envID, env);
			if (this.getEnvironments()==null)
				this.setEnvironments(new Environments());
			this.getEnvironments().getEnvironments().add(env);
		}
		return envID;
	}

	@Override
	public EnvironmentType getEnvironment(String envID) {
		if (environments!=null)
			return environments.get(envID);
		return null;
	}

	@Override
	public Iterator<EnvironmentType> getAllEnvironments() {
		if (environments!=null)
			return environments.values().iterator();
		return new ArrayList<EnvironmentType>().iterator();
	}

	
	
	/**
	 * Function-related methods
	 * Map envID->Environment 
	 */
	@XmlTransient
	Map<String,FunctionType> functions = new HashMap<String,FunctionType>();
	
	@Override
	public String addFunction(FunctionType function) {
		if (function==null) 
			return null;
		String functionID = function.getFunctionID();
		if (functionID!=null && !functions.containsKey(functionID)){
			functions.put(functionID, function);
			if (this.getFunctions()==null)
				this.setFunctions(new Functions());
			this.getFunctions().getFunctions().add(function);
		}
		return functionID;
	}
	
	@Override
	public FunctionType getFunction(String functionID) {
		if (functions!=null)
			return functions.get(functionID);
		return null;
	}

	@Override
	public Iterator<FunctionType> getAllFunctions() {
		if (functions!=null)
			return functions.values().iterator();
		return new ArrayList<FunctionType>().iterator();
	}


	
	
	/**
	 * Method-related methods
	 */
	@XmlTransient
	Map<String,MethodType> methods = new HashMap<String,MethodType>();

	
	@Override
	public String addMethod(MethodType method) {
		if (method==null) 
			return null;
		String methodID = method.getMethodID();
		if (methodID!=null && !methods.containsKey(methodID)){
			methods.put(methodID, method);
			if (this.getMethods()==null)
				this.setMethods(new Methods());
			this.getMethods().getMethods().add(method);
		}
		return methodID;
	}
	
	@Override
	public MethodType getMethod(String methodID) {
		if (methods!=null)
			return methods.get(methodID);
		return null;
	}
	
	@Override
	public Iterator<MethodType> getAllMethods() {
		if (methods!=null)
			return methods.values().iterator();
		return new ArrayList<MethodType>().iterator();
	}


	
	/**
	 * Method-related methods
	 */
	@XmlTransient
	Map<String,SourceType> sources = new HashMap<String,SourceType>();

	@Override
	public String addSource(SourceType source) {
		if (source==null) 
			return null;
		String sourceID = source.getSourceID();
		if (sourceID!=null && !sources.containsKey(sourceID)){
			sources.put(sourceID, source);
			if (this.getSources()==null)
				this.setSources(new Sources());
			this.getSources().getSources().add(source);
		}
		return sourceID;
	}
	
	@Override
	public SourceType getSource(String sourceID) {
		if (sources!=null)
			return sources.get(sourceID);
		return null;
	}

	@Override
	public Iterator<SourceType> getAllSources() {
		if (sources!=null)
			return sources.values().iterator();
		return new ArrayList<SourceType>().iterator();
	}
	
	
	/**
	 * Uniform method for adding species
	 */
	@XmlTransient
	private Map<String,SpeciesInterface> allspecies = new HashMap<String,SpeciesInterface>();//Element and it's elementId
	@XmlTransient
	private Map<String,Object> realspecies = new HashMap<String,Object>();//Element and it's elementId
	
	@Override
	public String addElement(SpeciesInterface species) {
		if (species==null)
			return null;
		String speciesID=species.getSpeciesID();
		if (speciesID==null)
			return null;
		Object stored = this.allspecies.get(speciesID);
		if (stored==null){
			this.allspecies.put(speciesID, species);
			
			 
			//Create species branch if not exists
			if (this.getSpecies() ==null){
				this.setSpecies(new SpeciesType());
			};
			SpeciesType speciesroot = this.getSpecies();
			
			//Add element basing on it's type
			if (species instanceof MoleculeType){//Add molecule
				if (speciesroot.getMolecules()==null){
					speciesroot.setMolecules(new Molecules());
				}
				speciesroot.getMolecules().getMolecules().add((MoleculeType)species);
				this.realspecies.put(speciesID, species);
				
			}else if (species instanceof AtomType){//Or atom
				if (speciesroot.getAtoms()==null){
					speciesroot.setAtoms(new Atoms());
				}
				speciesroot.getAtoms().getAtoms().add((AtomType)species);
				this.realspecies.put(speciesID, ((AtomType)species).getIsotopes().get(0).getIons().get(0));
				
			}else if (species instanceof ParticleType){
				//Or particle, for which we have still a dirty hack, replacing speciesid with stateid 
				if (speciesroot.getParticles()==null){
					speciesroot.setParticles(new Particles());
				}
				speciesroot.getParticles().getParticles().add((ParticleType)species);
				this.realspecies.put(speciesID, ((ParticleType)species).getSpeciesID());
				
			}else if (species instanceof SolidType){
				//Or solid, for which we have still a dirty hack, replacing speciesid with stateid 
				if (speciesroot.getSolids()==null){
					speciesroot.setSolids(new Solids());
				}
				speciesroot.getSolids().getSolids().add((SolidType)species);
				this.realspecies.put(speciesID, ((SolidType)species).getSpeciesID());
			}
		}
		return speciesID;
	}
	
	@Override
	public SpeciesInterface getElement(String speciesID) {
		if (allspecies!=null)
			return allspecies.get(speciesID);
		return null;
	}


	
	/**
	 * States-related stuff 
	 */
	@XmlTransient
	private Map<String,StateInterface> allstates = new HashMap<String,StateInterface>();	
	@XmlTransient
	private Map<String,SpeciesInterface> stateElement = new HashMap<String,SpeciesInterface>();	//StateID and it's species
	
	
	@Override
	public String addState(String specieID,StateInterface state) {
		if (getCapacityStates()<=0)
			return null;
		
		SpeciesInterface specie = this.getElement(specieID);
		if (specie instanceof MoleculeType && state instanceof MolecularStateType)
		{//Add state to molecule
			((MoleculeType) specie).getMolecularStates().add((MolecularStateType)state);
			allstates.put(state.getStateID(),state);
			stateElement.put(state.getStateID(), specie);
			return state.getStateID();
		}else if (specie instanceof AtomType && state instanceof AtomicStateType)
		{//Add state to atom, state structure ion/isotope structure should be already created.
			AtomType atom = (AtomType) specie;
			if (atom.getIsotopes() != null 
					&& atom.getIsotopes().size()>0 
					&& atom.getIsotopes().get(0).getIons()!=null 
					&& atom.getIsotopes().get(0).getIons().size()>0
					&& atom.getIsotopes().get(0).getIons().get(0)!=null
					&& atom.getIsotopes().get(0).getIons().get(0).getAtomicStates()!=null){
				
			
				atom.getIsotopes().get(0).getIons().get(0).getAtomicStates().add((AtomicStateType) state);
				allstates.put(state.getStateID(),state);
				stateElement.put(state.getStateID(), specie);
				return state.getStateID();
			}
		}
		return null;
	}
	
	@Override
	public int addStates(String speciesID,Collection<? extends StateInterface> states) {
		Integer counter = 0;
		for (StateInterface state:states){
			if (this.getState(state.getStateID())==null 
					&& this.addState(speciesID,state)!=null)
				counter++;
			else break;
		}
		return counter;
	}
	
	@Override
	public StateInterface getState(String stateID) {
		if (allstates!=null)
			return allstates.get(stateID);
		return null;
	}

	@Override
	public Iterator<?> getSpeciesStates(String speciesID) {
		SpeciesInterface element = this.getElement(speciesID);
		//For molecule, return it's states
		if (element instanceof MoleculeType){
			MoleculeType mol = (MoleculeType) element;
			return mol.getMolecularStates().iterator();
		}
		//Do the same for atoms
		if (element instanceof AtomType){
			AtomType atom = (AtomType) element;
			List<?> states = null;
			try{
				states = atom.getIsotopes().get(0).getIons().get(0).getAtomicStates();
			}catch (Exception e){	
			}
			if (states!=null)
				return states.iterator();
		}
		
		return new ArrayList<Object>().iterator();
	}

	@Override
	public SpeciesStateRefType getSpeciesRef(String speciesID) {
		SpeciesStateRefType ssrt = new SpeciesStateRefType();
		ssrt.setSpeciesRef(this.realspecies.get(speciesID));
		return ssrt;
	}

	@Override
	public SpeciesStateRefType getStateRef(String stateID) {
		SpeciesStateRefType ssrt = new SpeciesStateRefType();
		ssrt.setStateRef(this.getState(stateID));
		SpeciesInterface sinf = this.stateElement.get(stateID);
		if (sinf!=null)
			ssrt.setSpeciesRef(
				this.realspecies.get(
						sinf.getSpeciesID()));
		//Use realspecies to get a real atom specie ref
		return ssrt;
	}

	
	@Override
	public Iterator<AtomType> getAllAtoms() {
		if (this.getSpecies() !=null && this.getSpecies().getAtoms()!=null)
			return this.getSpecies().getAtoms().getAtoms().iterator();
		return new ArrayList<AtomType>().iterator();
	}

	@Override
	public Iterator<MoleculeType> getAllMolecules() {
		if (this.getSpecies() !=null && this.getSpecies().getMolecules()!=null)
			return this.getSpecies().getMolecules().getMolecules().iterator();
		return new ArrayList<MoleculeType>().iterator();
	}

	@Override
	public Iterator<ParticleType> getAllParticles() {
		if (this.getSpecies() !=null && this.getSpecies().getParticles()!=null)
			return this.getSpecies().getParticles().getParticles().iterator();
		return new ArrayList<ParticleType>().iterator();
	}
	
	@Override
	public Iterator<SolidType> getAllSolids() {
		if (this.getSpecies() !=null && this.getSpecies().getParticles()!=null)
			return this.getSpecies().getSolids().getSolids().iterator();
		return new ArrayList<SolidType>().iterator();
	}

	
	
	/**
	 * Processes stuff
	 */
	@XmlTransient
	private List<Object> allprocesses = new ArrayList<Object>();		//Element and it's elementId
	
	
	@Override
	public boolean addProcess(Object process) {
		if (getCapacityProcesses()<=0)
			return false;

		if (this.getProcesses() == null)
			this.setProcesses(new ProcessesType());
		ProcessesType processes = this.getProcesses();
		
		if (process instanceof CollisionalTransitionType){
			if (processes.getCollisions() == null)
				processes.setCollisions(new Collisions());
			processes.getCollisions().getCollisionalTransitions().add((CollisionalTransitionType)process);
			allprocesses.add(process);
			return true;
		}else if (process instanceof RadiativeTransitionType){
			if (processes.getRadiative()==null)
				processes.setRadiative(new Radiative());
			processes.getRadiative().getRadiativeTransitions().add((RadiativeTransitionType) process);
			allprocesses.add(process);
			return true;
		}else if (process instanceof NonRadiativeTransitionType){
			if (processes.getNonRadiative()==null)
				processes.setNonRadiative(new NonRadiative());
			processes.getNonRadiative().getNonRadiativeTransitions().add((NonRadiativeTransitionType) process);
			allprocesses.add(process);
			return true;
		}

		return false;
	}

	/**
	 * @return remaining element states count till we will start to refuse adding states
	 */
	private int getCapacityStates(){
		if (XSAMSSettings.statesLimit.getIntValue()>=0)
			return XSAMSSettings.statesLimit.getIntValue()-getCountStates();
		return Integer.MAX_VALUE;
	}

	/**
	 * @return remaining transitions count till we will start to refuse adding new transitions
	 */
	private int getCapacityProcesses(){
		if (XSAMSSettings.processesLimit.getIntValue()>=0)
			return XSAMSSettings.processesLimit.getIntValue()-getCountProcesses();
		return Integer.MAX_VALUE;
	}

	private int getCountStates(){
		return allstates.size();
	}
	
	private int getCountProcesses(){
		return allprocesses.size();
	}




	





	
}
