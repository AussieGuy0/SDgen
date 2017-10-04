package au.com.anthonybruno.definition;

import au.com.anthonybruno.generator.Generator;

/**
 * The RecordDefinition interface adds data about the fields or specifies the number of rows the data will be generated for
 * This interface wants to see an addField method and a generate method implemented.
 * The addField method takes the name of the field and type of generator and
 * the generate method specifies the number of rows needed for generation
 * 
 * @author Anthony Bruno
 *
 */
public interface RecordDefinition {

	/**
	 * this abstract constructor takes in parameters that name the field and the type of values required to be generated
	 * 
	 * @param 	a String that names the field
	 * @param 	a Generator object that gives the type of values needed for generation
	 */
    RecordDefinition addField(String name, Generator generator);

    /**
     * this abstract method wants a method that takes an int and returns an object referenced by FileTypeDefintion interface
     * which will state the number of rows to be generated 
     * 
     * @param num	an int that indicates the number of rows needed
     * @return		an object referenced by FileTypeDefintion interface which specifies number of rows to be generated
     */
    FileTypeDefinition generate(int num);
}
