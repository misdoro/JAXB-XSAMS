package org.vamdc.xsams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.vamdc.xsams.schema.ObjectFactory;
import org.vamdc.xsams.schema.SourceType;

public abstract class PrimaryTypeBase extends BaseClass{
	public abstract List<JAXBElement<Object>> getSourceReves();

	/**
	 * Adds source reference to this element
	 * @param {@link SourceType} source. Don't forget to add it to the XSAMSData separately! 
	 */
	public void addSource(SourceType source){
		if (source!=null){
			ObjectFactory of = new ObjectFactory();
			this.getSourceReves().add(of.createPrimaryTypeSourceRef(source));
		}
	}

	/**
	 * Adds multiple source references to this element
	 * @param sources Collection<{@link SourceType}>  Don't forget to add all sources to the XSAMSData separately! 
	 */
	public void addSources(Collection<SourceType> sources){
		if (sources!=null){
			for (SourceType source:sources){
				this.addSource(source);
			}
		}
	}

	/**
	 * Get all sources of this element. This list is a copy, modifying it won't change the object! 
	 * @return Collection<SourceType>
	 */
	public Collection<SourceType> getSources(){
		Collection<SourceType> sources = new ArrayList<SourceType>();
		if (this.getSourceReves()!=null)
			for (JAXBElement<Object> source:this.getSourceReves()){
				if (source.getValue() instanceof SourceType)
					sources.add((SourceType) source.getValue());
			}
		return sources;
	}

}
