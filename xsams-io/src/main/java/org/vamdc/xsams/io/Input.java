package org.vamdc.xsams.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.vamdc.xsams.schema.XSAMSData;

/**
 * XSAMS input methods
 * @author doronin
 */
public class Input {

	public static XSAMSData readFile(File source) throws JAXBException{
		Unmarshaller u = JAXBContextFactory.getUnmarshaller();
		return (XSAMSData) u.unmarshal(source);
	}
	
	public static XSAMSData readStream(InputStream source) throws JAXBException{
		if (source==null)
			throw new IllegalArgumentException("Input stream should not be null");
		Unmarshaller u = JAXBContextFactory.getUnmarshaller();
		return (XSAMSData) u.unmarshal(source);
	}
	
	/**
	 * Download XSAMS document from URL
	 * @param document URL to XSAMS document
	 * @param monitor monitor interface for download progress estimation, methods are called at the start, once per each megabyte of transfer and when document is ready
	 * @return XSAMS document
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static XSAMSData readURL(URL document,Monitor monitor) throws JAXBException, IOException{
		
		InputStream stream = openConnection(document);
		if (monitor!=null)
			stream = new MonitorInputStream(stream,monitor);
		return readStream(stream);
	}
	
	
	/**
	 * Open URL connection, with timeouts set
	 * @param address connection URL
	 * @return Stream of data
	 * @throws IOException 
	 */
	public static InputStream openConnection(URL adress) throws IOException{
		URLConnection conn = adress.openConnection();
		//Allow gzip encoding
		if (IOSettings.compress.getIntValue()==1)
			conn.setRequestProperty("Accept-Encoding", "gzip");
		//Set timeouts
		conn.setConnectTimeout(IOSettings.httpConnectTimeout.getIntValue());
		conn.setReadTimeout(IOSettings.httpDataTimeout.getIntValue());
		conn.setRequestProperty("User-Agent",IOSettings.userAgent.getStrVal());

		checkHttpResultCode(adress, conn);
		
		InputStream responseStream = conn.getInputStream();
		String contentEncoding = conn.getContentEncoding();
		if ("gzip".equalsIgnoreCase(contentEncoding)) {
			responseStream = new GZIPInputStream(responseStream);
		}
		return responseStream;
	}

	private static void checkHttpResultCode(URL adress, URLConnection conn)
			throws IOException {
		if (adress.getProtocol().equals("http")|| adress.getProtocol().equals("https")){
			HttpURLConnection httpc= (HttpURLConnection) conn;
			if (httpc.getResponseCode()!=200)
				throw new IOException("Server responded with code "+httpc.getResponseCode());
		}
	}
	
	public static InputStream getXSAMSAsInputStream(XSAMSData document){
		return new XSAMSInputStream(document).getStream();
	}
	
}
