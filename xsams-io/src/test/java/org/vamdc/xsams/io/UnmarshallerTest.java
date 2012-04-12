package org.vamdc.xsams.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import org.vamdc.xsams.schema.XSAMSData;

public class UnmarshallerTest {
	private XSAMSData xsams;
	private final static String XSAMSResourceFile="broadening-test.xml";

	@Before
	public void init(){
		xsams=null;
		JAXBContextFactory.getContext();
	}

	@Test
	public void loadXSAMSStream() throws JAXBException{
		InputStream stream = ClassLoader.getSystemResourceAsStream(XSAMSResourceFile);
		assertNotNull (stream);

		xsams = Input.readStream(stream);
		assertNotNull(xsams);
	}

	@Test
	public void loadXSAMSFile() throws JAXBException{
		URL fileUrl = ClassLoader.getSystemResource(XSAMSResourceFile);
		File file = new File(fileUrl.getPath());
		assertNotNull (file);
		assertTrue(file.exists());

		xsams = Input.readFile(file);

		assertNotNull(xsams);
	}


}
