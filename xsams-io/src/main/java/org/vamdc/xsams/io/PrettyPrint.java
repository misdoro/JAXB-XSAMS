package org.vamdc.xsams.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Class to pretty-print XML
 * @author doronin
 *
 */
public class PrettyPrint implements Runnable{
	private Transformer transformer;
	private Source src=null;
	private StreamResult xmlOutput;
	private PipedInputStream in;
	private PipedOutputStream out;
	
	public PrettyPrint(){
		try {
			TransformerFactory transFactory = TransformerFactory.newInstance();
			transFactory.setAttribute("indent-number",new Integer(2));
			transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); 
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}  

	}

	public InputStream transform(InputStream source){
		if (src == null ){
			//Setup streams
			try{
				src = new StreamSource(new NoSpaceStream(source));
				in = new PipedInputStream();
				out = new PipedOutputStream(in);
				xmlOutput = new StreamResult(new OutputStreamWriter(out, "utf-8"));

			} catch (IOException e) {
				e.printStackTrace();
			}
			new Thread(this).start();
			return in;
		}else{
			return source;
		}
	}

	@Override
	public void run() {
		try {
			transformer.transform(src, xmlOutput);
			out.close();
			src = null;
		} catch (Exception e) {
			try {
				out.close();
			} catch (IOException e1) {
			}
			src=null;
		}

	}
	
	/**
	 * Class to remove extra space between tags, 
	 * needed to make prettyprinter work correctly
	 * 
	 * outputs data from input stream, if not in tag then skip all empty space if there is only empty space between tags.
	 * @author doronin
	 *
	 */
	private class NoSpaceStream extends InputStream{
		private byte[] buffer;
		private int index;

		private InputStream backStream;

		private int quote='\0';//Quote used
		private boolean backslash=false;//Is this symbol backslashed?
		private boolean intag=false;

		public NoSpaceStream(InputStream back){
			buffer=new byte[0];
			index=0;
			backStream = back;

		}

		@Override
		public int read() throws IOException {

			//If we have buffer filled, return data from it:
			if (index<buffer.length)
				return buffer[index++];

			//else if in tag, look for end of tag, read bytes normally
			if (intag){
				//Read a byte
				int newbyte = backStream.read();
				//Check if we encounter quotes:
				if (newbyte == '\'' || newbyte == '"' && !backslash){
					if (quote == newbyte)
						quote='\0';//We are out of quotes now
					else if (quote=='\0')
						quote = newbyte;//Entered quotes
				}

				//Check if symbol is backslashed, just return if yes
				if (backslash){
					backslash=false;
				}else if (newbyte=='\\') backslash=true;

				if (newbyte == '>' && quote=='\0')
					intag=false;//Getting out of tag
				return newbyte;
			}else{
				int newbyte='\0';
				//Buffer to keep data that occured between tags and consisted of not only empty space
				ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
				//Not in tag, read all data up to next tag start, decide if they make sense
				while ((newbyte = backStream.read())!='<' && newbyte!=-1){
					bytestream.write(newbyte);
				}
				//if (newbyte!=-1)
				bytestream.write(newbyte);
				byte[] bytes = bytestream.toByteArray();
				boolean hasdata=false;
				for (int i=0;i<bytes.length-1;i++){
					byte thisbyte = bytes[i];
					if (thisbyte!='\n' && thisbyte!='\r' && thisbyte!='\t' && thisbyte!=' '){
						hasdata=true;
						break;
					}
				}

				intag=true;//Set intag to true since we must have encountered new tag

				if (hasdata){//Print first byte of buffer if we got some data
					buffer=bytes;
					index=1;
					return buffer[0];
				}else{
					//Otherwise start a new tag, go on
					return newbyte;
				}	
			}
		}

	}
	
}
