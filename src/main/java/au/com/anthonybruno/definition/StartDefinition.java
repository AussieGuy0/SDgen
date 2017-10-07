package au.com.anthonybruno.definition;

import au.com.anthonybruno.generator.Generator;

/**
 * A class to specify the way that fields are configured, either through a class or specified individually.
 *
 * @author Anthony Bruno
 */
public interface StartDefinition { //FIXME: better name

    /**
     * Specifies a class that will be used to generate fields.
     *
     * @param c the class which the random data is to be generated for
     *
     * @return a RecordDefinition that allows the specification of the number of records to generate
     */
    RecordDefinition use(Class<?> c);

    /**
     * Adds a field to be generated.
     *
     * @param name      String that names the field
     * @param generator Generator object that creates random values for the field
     *
     * @return A FieldDefinition that allows more fields to be added
     */
    FieldDefinition addField(String name, Generator generator);
}
