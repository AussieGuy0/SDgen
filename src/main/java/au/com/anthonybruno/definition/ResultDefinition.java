package au.com.anthonybruno.definition;

import java.io.File;

/**
 * Specifies how the generated data will be stored. Any method used here will start the data generation process.
 *
 * @author Anthony Bruno
 */
public interface ResultDefinition {

    /**
     * Saves the generated data to the specified File and returns it.
     *
     * @param file a File where the generated data will be written to
     * @return a File containing the generated data
     */
    File toFile(File file);

    /**
     * Saves the generated date to a File specified by the path and returns it.
     *
     * @param path The directory path where the file will be saved
     * @return a File containing the generated data
     */
    File toFile(String path);

    /**
     * Returns the generated data as a String.
     *
     * @return a String containing the generated data
     */
    String toStringForm();
}
