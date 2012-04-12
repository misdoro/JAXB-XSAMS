package org.vamdc.xsams.io;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

class NSPrefixMapper extends NamespacePrefixMapper{

	private final static String XSAMSNS = "http://vamdc.org/xml/xsams/0.3";
	private final static String XSAMSCaseNS = "http://vamdc.org/xml/xsams/0.3/cases/";

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		// I want this namespace to be mapped to "xsi"
		if( "http://www.w3.org/2001/XMLSchema-instance".equals(namespaceUri) )
			return "xsi";

		//XSAMS root namespace
		if( XSAMSNS.equals(namespaceUri) )
			return null;

		//CML namespace
		if( "http://www.xml-cml.org/schema".equals(namespaceUri) )
			return "cml";

		//XSAMS QN Case namespace
		if (namespaceUri!=null && namespaceUri.startsWith(XSAMSCaseNS)){
			return namespaceUri.substring(XSAMSCaseNS.length());
		}

		// otherwise I don't care. Just use the default suggestion, whatever it may be.
		return suggestion;

	}

}
