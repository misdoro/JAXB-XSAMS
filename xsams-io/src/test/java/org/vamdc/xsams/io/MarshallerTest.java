package org.vamdc.xsams.io;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import org.vamdc.xsams.schema.XSAMSData;

public class MarshallerTest {


	private final static String XSAMSResourceFile="broadening-test.xml";
	
	@Before
	public void init(){
		JAXBContextFactory.getContext();
	}

	@Test
	public void testCaseNSPrefix() throws JAXBException{
		XSAMSData document = loadXSAMS();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Output.writeStream(document, os);
		
		String doc = os.toString();

		assertTrue(doc.contains("<lpcs:QNs>"));
	}

	private XSAMSData loadXSAMS() throws JAXBException{
		InputStream stream = ClassLoader.getSystemResourceAsStream(XSAMSResourceFile);
		XSAMSData xsams=null;
		assertNotNull (stream);
		xsams = Input.readStream(stream);
		assertNotNull(xsams);
		return xsams;
	}

}
