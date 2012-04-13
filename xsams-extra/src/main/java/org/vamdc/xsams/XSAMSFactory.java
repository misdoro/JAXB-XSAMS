package org.vamdc.xsams;

import org.vamdc.xsams.util.XSAMSManagerImpl;

public class XSAMSFactory {
	public static XSAMSManager getXsamsManager(){
		return new XSAMSManagerImpl();
	}
}
