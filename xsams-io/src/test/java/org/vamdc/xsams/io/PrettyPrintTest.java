package org.vamdc.xsams.io;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Ignore;

public class PrettyPrintTest {

	static String spacePattern="[\\s\\n\\t]+";
	
	@Test
	public void testNoSpaceStream(){
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taga>  <tagb>valb</tagb> <tagc>valc</tagc><tagd/></taga>";
		try {
			PrettyPrint pretty=new PrettyPrint();
			InputStream nospace=pretty.new NoSpaceStream(getStream(xml));
			String result = getString(nospace);
			System.out.println(result);
			assertTrue(equalsNoSpace(xml,result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
	
	@Test
	public void testNoSpaceStreamNomatchtag(){
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?> 		<taga> <tagb> value <tagc> value2 </tagb> </taga>";
		String expected="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taga><tagb> value <tagc> value2 </tagb> </taga>";
		try {
			PrettyPrint pretty=new PrettyPrint();
			InputStream nospace=pretty.new NoSpaceStream(getStream(xml));
			String result = getString(nospace);
			System.out.println(result);
			assertNotNull(pretty.getTransformException());
			assertTrue(equalsNoSpace(xml,result));
			assertEquals(expected,result);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

	}
	
	
	@Test
	public void testPrettyStandard(){
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taga>  <tagb>valb</tagb> <tagc>valc</tagc><tagd><tage/></tagd></taga>";
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taga>\n"+
		"  <tagb>valb</tagb>\n"+
		"  <tagc>valc</tagc>\n"+
		"  <tagd>\n"+
		"    <tage/>\n"+
		"  </tagd>\n"+
		"</taga>\n";
		
		try {
			InputStream prettyStream=new PrettyPrint().transform(getStream(xml));
			String result = getString(prettyStream);
			System.out.println(result);
			assertTrue(equalsNoSpace(xml,result));
			assertEquals(result,expected);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testPrettyNoMatchTag(){
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taga><tagb>value<tagc>value2</tagb></taga>";
		
		PrettyPrint pretty=new PrettyPrint();
		try {
			InputStream prettyStream=pretty.transform(getStream(xml));
			String result = getString(prettyStream);
			System.out.println(result);
			assertNotNull(pretty.getTransformException());
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			//e.printStackTrace();
		}
	}
	
	@Test
	public void testPrettyComments(){
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taga><!-- <tagb>value<tagc>value2</tagc>\n</tagb>--><tagc>a</tagc></taga>";
		String expected="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taga>\n"+
		"  <!-- <tagb>value<tagc>value2</tagc>\n</tagb>-->\n"+
  		"  <tagc>a</tagc>\n"+
  		"</taga>\n";
		PrettyPrint pretty=new PrettyPrint();
		try {
			InputStream prettyStream=pretty.transform(getStream(xml));
			String result = getString(prettyStream);
			assertTrue(equalsNoSpace(xml,result));
			assertEquals(result,expected);
			System.out.println(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	private boolean equalsNoSpace(String string1,String string2){
		String nsp1 = string1.replaceAll(PrettyPrintTest.spacePattern,"");
		String nsp2 = string2.replaceAll(PrettyPrintTest.spacePattern,"");
		return nsp1.equals(nsp2);
	}
	
	private InputStream getStream(String xml) throws UnsupportedEncodingException{
			return new ByteArrayInputStream(xml.getBytes("utf-8"));
		
	}
	
	private String getString(InputStream stream) throws IOException{
		return IOUtils.toString(stream,"utf-8");
	}
	
}
