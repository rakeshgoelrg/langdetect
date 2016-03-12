/**
 * 
 */
package goelr.langdetect;

import java.io.File;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import goelr.langdetect.helper.LanguageHelper;
import goelr.langdetect.model.DetectionResult;
import goelr.langdetect.model.LanguageSamples;

/**
 * @author goelr
 *
 */
public class DetectorImpl implements Detector {

	
	/**
	 * {@inheritDoc}
	 */
	public DetectionResult detectLanguage(LanguageSamples langSampleFiles, File unknownLangFile, LanguageHelper languageHelper) {
		DetectionResult result = new DetectionResult();
		List<String> words = languageHelper.getWords(unknownLangFile);
		if (CollectionUtils.isEmpty(words)) {
			return result;
		}
		result.setTotalCount(words.size());
		result.setLanguageName(langSampleFiles.getLanguageName());
		
		for (File langSampleFile: langSampleFiles.getSamples()) {
			List<String> langWords = languageHelper.getWords(langSampleFile);
			words.removeAll(langWords);
		}
		
		result.setMatchingCount(result.getTotalCount() - words.size());
		return result;
	}

}
