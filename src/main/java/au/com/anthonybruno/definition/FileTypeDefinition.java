package au.com.anthonybruno.definition;

import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;

/**
 * Defines the file format of the generated data.
 *
 * @author Anthony Bruno
 */
public interface FileTypeDefinition {

    /**
     * Sets the format to csv.
     *
     * @return a ResultDefinition object to specify how the generated data is saved.
     */
    ResultDefinition asCsv();

    /**
     * Sets the format to csv with configuration options given by the specified settings object.
     *
     * @param settings a CsvSettings object which configures how the csv is created such as whether or include a heading row.
     *
     * @return a ResultDefinition object to specify how the generated data is saved.
     */
    ResultDefinition asCsv(CsvSettings settings);

    /**
     * Sets the format to be fixed width with configuration options given by the specified settings object.
     *
     * @param fixedWidthSettings a FixedWidthSettings object that specifies the fixed width desired
     *
     * @return a ResultDefinition object to specify how the generated data is saved.
     */
    ResultDefinition asFixedWidth(FixedWidthSettings fixedWidthSettings);
}
