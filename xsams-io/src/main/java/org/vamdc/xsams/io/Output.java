package org.vamdc.xsams.io;

import java.io.File;
import java.io.OutputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.vamdc.xsams.schema.XSAMSData;

public class Output {
	
	public static void writeFile(XSAMSData xsams, File destination) throws JAXBException{
		Marshaller m = JAXBContextFactory.getMarshaller();
		m.marshal(xsams, destination);
	}
	
	public static void writeStream(XSAMSData xsams, OutputStream output) throws JAXBException{
		Marshaller m = JAXBContextFactory.getMarshaller();
		m.marshal(xsams, output);
	}
	
	
}
