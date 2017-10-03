package au.com.anthonybruno.defintion;

import java.io.File;

/**
 * This ResultDefintion interface is about setting the pathway to the file where the randomly generated data will be held
 * This interface wants to see a toFile() method and a toStringForm() method implemented
 * 
 * @author Anthony Bruno
 *
 */
public interface ResultDefinition {

	/**
	 * this abstract method creates a file pathway
	 * 
	 * @param file	a File object which gives the pathway
	 * @return		a File object which has the pathway to the file where the randomly generated data is to be held
	 */
    File toFile(File file);

    /**
     * this abstract method returns a String which shows the file pathway information
     * 
     * @return	a String which gives the file pathway information
     */
    String toStringForm();
}
