package au.com.anthonybruno.definition;

import au.com.anthonybruno.generator.Generator;

/**
 * FieldData - A class that holds the properties for name and generator
 * @author Anthony Bruno
 *
 */
public class FieldData {

    private final String name;
    private final Generator generator;

    /**
     * FieldData Constructor creates a FieldData object with the parameters name and generator
     * 
     * @param name 		A String to give the generator a name
     * @param generator a Generator object to choose a generator type
     */
    public FieldData(String name, Generator generator) {
        this.name = name;
        this.generator = generator;
    }

    /**
     * this getter method returns the field name
     * 
     * @return 	a String which is the specific name given to this field
     */
    public String getName() {
        return name;
    }

    /**
     * this getter method returns the classes' generator object
     * 
     * @return the generator object 
     */
    public Generator getGenerator() {
        return generator;
    }
}
