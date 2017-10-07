package au.com.anthonybruno.definition;

import au.com.anthonybruno.generator.Generator;

/**
 * The FieldDefinition defines the way that fields are generated, either through a class or specified individually
 * the use() method to define the class the field belongs to, or
 * the addField() method to define the name of the field and the type of the generation
 * 
 * @author Anthony Bruno
 *
 */
public interface FieldDefinition {

	/**
	 * Abstract method asks user to specify class to generate data for
	 * 
	 * @param c 	the class which the random data is to be generated for
	 * @return 		returns an object that is referenced by RecordDefinition that knows which class to use
	 */
    RecordDefinition use(Class<?> c);

    /**
     * Abstract method takes two parameters to create the data for a field
     * 
     * @param name String that names the field
     * @param generator Generator object that gives the type of values needed for generation
     * @return 	an object referenced by RecordDefinition with name and generator
     */
    RecordDefinition addField(String name, Generator generator);
}
