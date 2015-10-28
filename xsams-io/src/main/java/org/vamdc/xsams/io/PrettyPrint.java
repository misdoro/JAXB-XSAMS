package org.vamdc.xsams.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Stack;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
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
	private Throwable exception;

	public PrettyPrint(){
		try {
			TransformerFactory transFactory = TransformerFactory.newInstance();
			//transFactory.setAttribute("indent-number",new Integer(2));
			transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); 
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}  

	}
	
	public Throwable getTransformException(){
		return exception;
	}

	public static InputStream transformStatic(InputStream source){
		return new PrettyPrint().transform(source);
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
			} catch (TransformerException e) {
				try {
					out.close();
				} catch (IOException e1) {}
				src = null;
				if (e.getCause()!=null)
					this.exception=e.getCause();
			} catch (IOException e) {
				e.printStackTrace();
				this.exception=e;
			}
			src = null;
	}
	
	/**
	 * Class to remove extra space between tags, 
	 * needed to make prettyprinter work correctly
	 * 
	 * outputs data from input stream,
	 * if not in tag then skip all empty space if there is only empty space between tags.
	 * 
	 * Throw IllegalArgumentException if encounter not matching start and end tags
	 * @author doronin
	 *
	 */
	protected class NoSpaceStream extends InputStream{
		private byte[] buffer;
		private int readIndex;

		private InputStream backStream;
		private boolean hasFailed=false;
		private int quote='\0';//Quote used
		private boolean wasEscaped=false;//Is this symbol backslashed?
		private boolean intag=false;
		private boolean incomment=false;
		private Stack<String> tagStack=new Stack<String>();
		private Stack<String> fullTagStack=new Stack<String>();
		private StringBuilder tag = new StringBuilder();

		public NoSpaceStream(InputStream back){
			buffer=new byte[0];
			readIndex=0;
			backStream = back;

		}

		@Override
		public int read() throws IOException {

			//If we have buffer filled, return data from it:
			if (readIndex<buffer.length)
				return buffer[readIndex++];

			//If we failed, fall back to backStream
			if (hasFailed)
				return backStream.read();
			
			//else if in tag, look for end of tag, read bytes normally
			if (intag){
				//Read a byte
				int newbyte = backStream.read();
				//Check if we encounter quotes:
				if (newbyte == '\'' || newbyte == '"' && !wasEscaped){
					if (quote == newbyte)
						quote='\0';//We are out of quotes now
					else if (notInQuote())
						quote = newbyte;//Entered quotes
				}

				//Check if escaping next symbol
				wasEscaped=isEscape(newbyte);
				
				
				if (newbyte == '>' && notInQuote()){
					processTagName();
					intag=incomment;//Getting out of tag
				}else{
					tag.append((char)newbyte);
				}
				
				return newbyte;
			}else{
				int newbyte='\0';
				//Buffer to keep data that occured between tags and consisted of not only empty space
				ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
				//Not in tag, read all data up to next tag start, decide if it makes sense
				while ((newbyte = backStream.read())!='<' && newbyte!=-1){
					bytestream.write(newbyte);
				}
				
				byte[] bytes = bytestream.toByteArray();
				boolean hasdata=false;
				for (byte abyte:bytes){
					if (!isASpace(abyte)){		
						hasdata=true;
						break;
					}
				}
				
				bytestream.write(newbyte);
				bytes = bytestream.toByteArray();
				intag=true;//Set intag to true since we must have encountered new tag
				
				if (hasdata){//Print first byte of buffer if we got some data
					buffer=bytes;
					readIndex=1;
					return buffer[0];
				}else{
					return newbyte;
					
				}	
			}
		}

		private void processTagName() throws IOException {
			String fullTagName=tag.toString();
			if (fullTagName.startsWith("!--")){
				incomment=!fullTagName.endsWith("--");
				return;
			}
			
			tag=new StringBuilder();
			

			if (fullTagName.startsWith("/")){
				processEndTag(fullTagName.substring(1));
			}else if (!fullTagName.endsWith("/")){
				fullTagStack.push(fullTagName);
				tagStack.push(fullTagName.split("[ \\t\\n\\x0B\\f\\r>]")[0]);
			}
			
		}

		private void processEndTag(String tag) throws IOException {
			String startTag=tagStack.pop();
			if (startTag.equals(tag)){
				fullTagStack.pop();
			}else{
				hasFailed=true;
				buffer=("</"+tag+">").getBytes();
				readIndex=0;
				if (fullTagStack.size()>2){
					fullTagStack.remove(0);
					fullTagStack.remove(0);
				}
				throw new IllegalArgumentException
				("Pretty printer failure:\n" +
					"Not matching tags: opening <"+startTag+"> and closing </"+tag+">.\n" +
					"Problem at: "+fullTagStack);
			}
		}

		private boolean notInQuote() {
			return quote=='\0';
		}

		private boolean isEscape(int newbyte) {
			return newbyte=='\\';
		}

		private boolean isASpace(byte thisbyte) {
			return thisbyte=='\n' || thisbyte=='\r' || thisbyte=='\t' || thisbyte==' ';
		}

	}
	
}
