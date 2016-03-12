/**
 * 
 */
package goelr.langdetect;

import java.io.File;

import goelr.langdetect.helper.LanguageHelper;
import goelr.langdetect.model.DetectionResult;
import goelr.langdetect.model.LanguageSamples;

/**
 * @author goelr
 * language detector
 */
public interface Detector {

	/**
	 * match the language of words in language samples
	 * @param langSamples language samples
	 * @param unknownLangFile file detect language
	 * @param languageHelper  languageHelper
	 * @return matching result
	 */
	DetectionResult detectLanguage(LanguageSamples langSamples, File unknownLangFile, LanguageHelper languageHelper);
}
