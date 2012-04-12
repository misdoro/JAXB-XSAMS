package org.vamdc.xsams.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter class for xs:integer
 * 
 */
public class IntegerAdapter
    extends XmlAdapter<String, Integer>
{


    public Integer unmarshal(String value) {
        return (javax.xml.bind.DatatypeConverter.parseInt(value));
    }

    public String marshal(Integer value) {
        if (value == null) {
            return null;
        }
        return (javax.xml.bind.DatatypeConverter.printInt(value));
    }

}
