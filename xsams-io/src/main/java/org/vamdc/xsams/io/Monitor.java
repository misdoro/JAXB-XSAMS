package org.vamdc.xsams.io;

/**
 * Monitor interface, implementation can be attached to input readURL to monitor the document download progress
 * @author doronin
 *
 */
public interface Monitor {
	/**
	 * Method called when Model starts fetching XSAMS document
	 */
	public void started();
	
	/**
	 * Method called every 1000 lines/every 1MB of processed input XSAMS document.
	 */
	public void tick();
	
	/**
	 * Method called when XSAMS document becomes ready
	 * @param bytesRead total count of bytes read 
	 */
	public void done(long bytesRead);
}
