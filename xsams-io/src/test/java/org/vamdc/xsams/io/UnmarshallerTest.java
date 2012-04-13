package org.vamdc.xsams.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import org.vamdc.xsams.schema.MolecularStateType;
import org.vamdc.xsams.schema.MoleculeType;
import org.vamdc.xsams.schema.RadiativeTransitionType;
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

	@Test
	public void testParentReference() throws JAXBException{
		xsams = Input.readStream(ClassLoader.getSystemResourceAsStream(XSAMSResourceFile));
		assertNotNull(xsams);
		assertNull(xsams.getParent());
		assertNotNull(xsams.getSpecies().getParent());
		assertTrue(xsams==xsams.getSpecies().getParent());
		RadiativeTransitionType transition = xsams.getProcesses().getRadiative().getRadiativeTransitions().get(0);
		assertNotNull(transition);
		Object state = transition.getUpperStateRef();
		assertNotNull(state);
		assertTrue(state instanceof MolecularStateType);
		MolecularStateType mstate = (MolecularStateType) state;
		assertNotNull(mstate.getParent());
		assertTrue(mstate.getParent() instanceof MoleculeType);
		MoleculeType molecule = (MoleculeType) mstate.getParent();
		assertNotNull(molecule.getMolecularChemicalSpecies().getChemicalName());
		System.out.println(molecule.getMolecularChemicalSpecies().getChemicalName().getValue());
	}

}
