package au.com.anthonybruno.definition;


/**
 * A class to specify how many records will be generated.
 *
 * @author Anthony Bruno
 */
public interface RecordDefinition {


    /**
     * Specifies how many records will be generated.
     *
     * @param num the int defining how many rows will be generated
     * @return A {@link FileTypeDefinition} which allows the specification of the type of file to create
     */
    FileTypeDefinition generate(int num);
}
