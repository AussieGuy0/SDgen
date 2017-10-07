package au.com.anthonybruno.definition;

import au.com.anthonybruno.generator.Generator;

/**
 * The RecordDefinition interface adds data about the fields or specifies the number of rows the data will be generated for
 * The addField method takes the name of the field and type of generator and
 * the generate method specifies the number of rows needed for generation
 *
 * @author Anthony Bruno
 */
public interface RecordDefinition {

    /**
     * Adds a field that will be used when generating data.
     *
     * @param name      the key of the field
     * @param generator the Generator that creates values for the field
     */
    RecordDefinition addField(String name, Generator generator);

    /**
     * Specifies how many records will be generated.
     *
     * @param num the int defining how many rows will be generated
     * @return A {@link FileTypeDefinition} which allows the specification of the type of file to create
     */
    FileTypeDefinition generate(int num);
}
