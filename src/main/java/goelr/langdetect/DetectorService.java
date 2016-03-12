/**
 * 
 */
package goelr.langdetect;

import java.io.File;
import java.io.FileNotFoundException;

import goelr.langdetect.helper.LanguageHelper;
import goelr.langdetect.model.DetectionResult;

/**
 * @author goelr
 * language detector service
 */
public interface DetectorService {

/**
 * 
 * @param unknownLangFileName name of file to test language
 * @param langSampleFilesPath custom path for sample files
 * @return result of best matching language
 * @throws FileNotFoundException if doesn't exist
 */
	DetectionResult detectLanguage(String unknownLangFileName, String langSampleFilesPath) throws FileNotFoundException;

	/**
	 * detect the language of supplied text file using sample files at provided
	 * path
	 * 
	 * @param unknownLangFile
	 *            file path
	 * @param langSampleFilesPath
	 *            provided sample files  path
	 * @return result of best matching language
	 */
	DetectionResult detectLanguage(File unknownLangFile, File langSampleFilesPath);
	
	/**
	 * set detector
	 * @param detector detector
	 */
	void setDetector(Detector detector);
	
	/**
	 * set language Helper
	 * @param languageHelper languageHelper
	 */
	void setLanguageHelper(LanguageHelper languageHelper);
}
