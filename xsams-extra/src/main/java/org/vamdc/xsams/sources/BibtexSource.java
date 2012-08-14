package org.vamdc.xsams.sources;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.jbibtex.*;

public class BibtexSource extends SourceType{
	
	public BibtexSource(String resource){
		super();
		
		BibTeXEntry entry = getBibEntry(resource);
		if (entry!=null){
			this.setTitle(getKeyValue(entry,"title"));
			
			this.setYear(Integer.valueOf(getKeyValue(entry,"year")));
			
			Value authors = entry.getField(new Key("author"));
			AuthorsType authorsType = new AuthorsType();
			if (authors!=null){
				for (String author:authors.toUserString().split("and")){
					authorsType.addAuthor(author);
				}
			}
			
			this.setAuthors(authorsType);
			this.setVolume(getKeyValue(entry, "volume"));
			this.setSourceName(getKeyValue(entry, "journal"));
			
		}
			
	}

	
	private static String getKeyValue(BibTeXEntry entry,String key){
		Value result = entry.getField(new Key(key));
		if (result!=null)
			return result.toUserString();
		return "";
	}

	protected BibTeXEntry getBibEntry(String resource) {
		try{
			String bibstr = read(resource);
			BibTeXDatabase bibdb = parseBibTeX(bibstr);
			BibTeXEntry entry = bibdb.getEntries().values().iterator().next();
			return entry;
		}catch (IOException e){
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private BibTeXDatabase parseBibTeX(String bibtex) throws IOException, ParseException {
        
		Reader reader = new StringReader(bibtex);
        try {
                BibTeXParser parser = new BibTeXParser();

                return parser.parse(reader);
        } finally {
                reader.close();
        }
	}
	
	private String read(String resource) throws IOException{
		InputStream in = this.getClass().getResourceAsStream(resource);
		StringBuilder result = new StringBuilder();
		while (in.available()>0){
			byte[] data = new byte[in.available()];
			in.read(data);
			result.append(new String(data,"UTF-8"));
		}
		return result.toString();
	}

}
