package au.com.anthonybruno.definition;

import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;

/**
 * This FileTypeDefinition interface provides methods for working with .csv files
 * This interface wants to see asCsv() or asCsv(args) and asFixedWidth(args) implemented
 * 
 * @author Anthony Bruno
 *
 */
public interface FileTypeDefinition {

	/**
	 * This abstract method returns a ResultDefinition object which directs the format to be .csv
	 * 
	 * @return ResultDefinition object that directs .csv to be the file format
	 */
    ResultDefinition asCsv();

    /**
     * This abstract method directs the format to be .csv with specified heading settings
     * 
     * @param settings 	a CsvSettings object which specifies whether or not to include a heading
     * @return 			an object referenced by ResultDefinition that specifies .csv format and whether it includes headings
     */
    ResultDefinition asCsv(CsvSettings settings);

    /**
     * This abstract method directs the format to be .csv with heading and fixed width settings
     * 
     * @param fixedWidthSettings 	a FixedWidthSettings object that specifies the fixed width desired
     * @return 						an object referenced by ResultDefinition object 
     */
    ResultDefinition asFixedWidth(FixedWidthSettings fixedWidthSettings);
}
