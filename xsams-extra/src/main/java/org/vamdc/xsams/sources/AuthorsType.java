package org.vamdc.xsams.sources;

import java.util.Collection;



public class AuthorsType extends org.vamdc.xsams.schema.AuthorsType {
		
		
		/**
		 * Default constructor
		 */
		public AuthorsType(){
			super();
		}

		/**
		 * Constructor which automatically adds authors from collection of string names 
		 * @param authors
		 */
		public AuthorsType(Collection<String> authors){
			super();
			for (String author:authors){
				addAuthor(author);
			}
		}
		
		/**
		 * Constructor which automatically adds authors from concatenated string with separator
		 * @param concatAuthors authors string
		 * @param separator separator, for example ", " or " and "
		 * 
		 */
		public AuthorsType(String concatAuthors, String separator){
			super();
			if (concatAuthors!=null && separator !=null){
				for (String author:concatAuthors.split(" and ")){
					this.addAuthor(author.trim());
				}
			}
		}
		
		/**
		 * Adds new author by it's name, returns this author object, so one can set any other field value 
		 * @param authorName value that will go into name element
		 */
		public AuthorsType addAuthor(String authorName){
			this.getAuthors().add(new AuthorType(authorName));
			return this;
		}
}
