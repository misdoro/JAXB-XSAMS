package org.vamdc.xsams.sources;

import static org.junit.Assert.*;

import org.junit.Test;

public class BibtexTest {

	@Test
	public void readKidaSource(){
		SourceType bibtex = new BibtexSource("/kida.bib");
		assertTrue(bibtex.getTitle().length()>0);
	}
	
}
