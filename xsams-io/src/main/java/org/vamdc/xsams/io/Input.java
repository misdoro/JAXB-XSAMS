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
		
		HttpURLConnection conn = (HttpURLConnection) document.openConnection();
		conn.setConnectTimeout(IOSettings.httpConnectTimeout.getIntValue());
		conn.setReadTimeout(IOSettings.httpDataTimeout.getIntValue());
		InputStream stream = conn.getInputStream();
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
		conn.setRequestProperty("Accept-Encoding", "gzip");
		//Set timeouts
		conn.setConnectTimeout(IOSettings.httpConnectTimeout.getIntValue());
		conn.setReadTimeout(IOSettings.httpDataTimeout.getIntValue());

		InputStream responseStream = conn.getInputStream();
		String contentEncoding = conn.getContentEncoding();
		if ("gzip".equalsIgnoreCase(contentEncoding)) {
			responseStream = new GZIPInputStream(responseStream);
		}
		return responseStream;
	}
	
}