package org.vamdc.xsams.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Factory to be giving out JAXB context, marshallers and unmarshallers.
 * @author doronin
 *
 */
public class JAXBContextFactory {

	private enum Context{
		INSTANCE;

		private final JAXBContext jc;

		Context(){
			JAXBContext newContext=null;
			try {

				newContext = JAXBContext.newInstance(org.vamdc.xsams.schema.XSAMSData.class);
			} catch (JAXBException e) {e.printStackTrace();}
			jc=newContext;
		}

		public JAXBContext getContext(){
			return jc;
		}

	}

	public static JAXBContext getContext(){
		return Context.INSTANCE.getContext();
	}

	public static Unmarshaller getUnmarshaller(){
		try {
			return getContext().createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Marshaller getMarshaller(){
		Marshaller m;
		try {
			m= getContext().createMarshaller();
			m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, IOSettings.prettyprint.getIntValue()>0);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
		return m;
	}

}
