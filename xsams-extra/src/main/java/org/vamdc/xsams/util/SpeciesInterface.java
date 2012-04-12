package org.vamdc.xsams.util;

/**
 * Interface for getting stateID of atomic, molecular states, particles and solids
 * @author doronin
 *
 */
public interface SpeciesInterface {
	String getSpeciesID ();
	void setSpeciesID(String speciesID);
}
