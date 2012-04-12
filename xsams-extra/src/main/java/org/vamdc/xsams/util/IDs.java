package org.vamdc.xsams.util;

/**
 * Factory to get ID strings with configured prefix, basing on some integer IDs 
 * @author Misha Doronin
 *
 */
public class IDs {

	public final static char SOURCE='B';
	public final static char ENVIRONMENT='E';
	public final static char SPECIE='X';
	public final static char FUNCTION='F';
	public final static char METHOD='M';
	public final static char STATE='S';
	public final static char MODE='V';
	public final static char PROCESS='P';
	
	/**
	 * Private constructor to deny instantiation and inheritance
	 * @throws InstantiationException 
	 */
	private IDs() throws InstantiationException{
		throw new InstantiationException();
	}
	/**
	 * Generate an arbitrary ID, using provided prefix and suffix.
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public static String getID(char prefix, String suffix){
		return new String(String.valueOf(prefix)+XSAMSSettings.idPrefix.getStrValue()+suffix);
	}
	
	/**
	 * Source ID
	 * @param idSource index of source record
	 * @return string identifier for source
	 */
	public static String getSourceID(int idSource){
		return getID(IDs.SOURCE,String.valueOf(idSource));
	}
	
	/**
	 * Environment ID
	 * @param idEnv
	 * @return
	 */
	public static String getEnvID(int idEnv){
		return getID(IDs.ENVIRONMENT,String.valueOf(idEnv));
	}
	
	/**
	 * Function ID
	 * @param idEnv
	 * @return
	 */
	public static String getFunctionID(int idFunction){
		return getID(IDs.FUNCTION,String.valueOf(idFunction));
	}
	
	/**
	 * Method ID
	 * @param idMethod
	 * @return
	 */
	public static String getMethodID(int idMethod){
		return getID(IDs.METHOD,String.valueOf(idMethod));
	}

	/**
	 * State ID
	 * @param EnergyTable index of energy table, if any. 0 otherwise
	 * @param Level level index (globally unique or per-energy table)
	 * @return string identifier for state
	 */
	public static String getStateID(int EnergyTable, int Level){
		return getID(IDs.STATE,"ET"+EnergyTable+"-"+Level);
	}
	
	/**
	 * Vibrational mode ID
	 * @param Species
	 * @param mode
	 * @return
	 */
	public static String getModeID(int molecule, int mode){
		return getID(IDs.MODE,"M"+molecule+"-"+mode);
	}
	
	/**
	 * Specie ID
	 * @param idSpecie index of specie
	 * @return string identifier for specie
	 */
	public static String getSpecieID(int idSpecies){
		return getID(IDs.SPECIE,String.valueOf(idSpecies));
	}
	

	/**
	 * Process ID
	 * @param group optional process group, may be like 'C' for collisions, 'R' for radiative transitions, 'N' for nonrad transitions
	 * @param idProcess index of process
	 * @return string identifier of process
	 */
	public static String getProcessID(char group, int idProcess){
		return getID(IDs.PROCESS,""+group+String.valueOf(idProcess));
	}
	
	
	
	
}
