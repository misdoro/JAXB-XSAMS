package org.vamdc.xsams.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.xml.bind.JAXBException;

import org.vamdc.xsams.schema.XSAMSData;

class XSAMSInputStream implements Runnable{
	PipedInputStream in;
	PipedOutputStream out;
	XSAMSData xsamsRoot;

	public XSAMSInputStream(XSAMSData xsams){
		try {
			in = new PipedInputStream();
			out = new PipedOutputStream(in);
			xsamsRoot=xsams;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if (xsamsRoot!=null)
			try {
				JAXBContextFactory.getMarshaller().marshal(xsamsRoot, out);
				out.close();
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public InputStream getStream() {
		if (xsamsRoot==null) return null;
		new Thread(this).start();
		return in;
	}
}
