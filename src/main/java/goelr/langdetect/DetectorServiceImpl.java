/**
 * 
 */
package goelr.langdetect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import goelr.langdetect.helper.LanguageHelper;
import goelr.langdetect.model.DetectionResult;
import goelr.langdetect.model.LanguageSamples;

/**
 * @author goelr
 *
 */
public class DetectorServiceImpl implements DetectorService {

	private static final String MSG_FILE_NOT_FOUND = "File not found.";
	private static final String MSG_LANGUAGE_SAMPLES_FILES_NOT_FOUND = "Language samples files not found.";

	private static Logger LOGGER = LoggerFactory.getLogger(DetectorServiceImpl.class);

	private LanguageHelper languageHelper;
	private Detector detector;

	/**
	 * {@inheritDoc}
	 */
	public DetectionResult detectLanguage(String unknownLangFileName, String langSampleFilesPathName)
			throws FileNotFoundException {
		
		File unknownLangFile = new File(unknownLangFileName);
		if (!unknownLangFile.exists() || unknownLangFile.isDirectory()) {
			LOGGER.error(MSG_FILE_NOT_FOUND);
			throw new FileNotFoundException(MSG_FILE_NOT_FOUND);
		}
		File langSampleFilesPath = new File(langSampleFilesPathName);
		if (!langSampleFilesPath.exists() || !langSampleFilesPath.isDirectory()) {
			LOGGER.error(MSG_LANGUAGE_SAMPLES_FILES_NOT_FOUND);
			throw new FileNotFoundException(MSG_LANGUAGE_SAMPLES_FILES_NOT_FOUND);
		}
		return detectLanguage(unknownLangFile, langSampleFilesPath);
	}

	/**
	 * {@inheritDoc}
	 */
	public DetectionResult detectLanguage(File unknownLangFile, File langSampleFilesPath) {

		List<LanguageSamples> langSampleFiles = languageHelper.loadLangSamples(langSampleFilesPath);
		SortedSet<DetectionResult> results = new TreeSet<DetectionResult>();
		for (LanguageSamples langSamples : langSampleFiles) {
			results.add(detector.detectLanguage(langSamples, unknownLangFile, languageHelper));
		}

		return processResult(results);
	}

	private DetectionResult processResult(SortedSet<DetectionResult> results) {
		DetectionResult finalResult = results.last();
		LOGGER.info("Best matching language is {}", finalResult.getLanguageName());
		LOGGER.info("Matching percentage is {}%", finalResult.getFormatedMatchingPercentage());

		return finalResult;
	}

	public void setDetector(Detector detector) {
		this.detector = detector;
	}
	
	public void setLanguageHelper(LanguageHelper languageHelper) {
		this.languageHelper = languageHelper;
	}

}
