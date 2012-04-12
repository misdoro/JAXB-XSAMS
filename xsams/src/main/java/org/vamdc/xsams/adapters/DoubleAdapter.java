package org.vamdc.xsams.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter class for xs:double
 * 
 */
public class DoubleAdapter
    extends XmlAdapter<String, Double>
{


    public Double unmarshal(String value) {
        return (javax.xml.bind.DatatypeConverter.parseDouble(value));
    }

    public String marshal(Double value) {
        if (value == null) {
            return null;
        }
        return (javax.xml.bind.DatatypeConverter.printDouble(value));
    }

}
