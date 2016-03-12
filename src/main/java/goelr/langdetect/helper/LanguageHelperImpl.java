/**
 * 
 */
package goelr.langdetect.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import goelr.langdetect.ApplicationProperties;
import goelr.langdetect.model.LanguageSamples;

/**
 * @author goelr
 *
 */
public class LanguageHelperImpl implements LanguageHelper {

	private static final String MSG_INVALID_LANGUAGE_SAMPLE_FILE_IGNORED = "Invalid language sample file. Ignored";
	private static Logger LOGGER = LoggerFactory.getLogger(LanguageHelperImpl.class);

	/**
	 * {@inheritDoc}
	 */
	public List<LanguageSamples> addLangSample(List<LanguageSamples> allSamples, File newSample) {
		return addLangSample(allSamples, newSample, getLanguageName(newSample));
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LanguageSamples> addLangSample(List<LanguageSamples> allLangSamples, File newLangSample, String languageName) {
		if (StringUtils.isBlank(languageName)) {
			LOGGER.info("Invalid Language name in sample file. File ignored");
			return allLangSamples;
		}
		if (CollectionUtils.isEmpty(allLangSamples)) {
			allLangSamples = new ArrayList<LanguageSamples>();
			allLangSamples.add(new LanguageSamples(languageName, newLangSample));
			return allLangSamples;
		}

		for (LanguageSamples lSamples : allLangSamples) {
			if (languageName.equalsIgnoreCase(lSamples.getLanguageName())) {
				lSamples.getSamples().add(newLangSample);
				return allLangSamples;
			}
		}

		// add new language entry as not added so far
		allLangSamples.add(new LanguageSamples(languageName, newLangSample));

		return allLangSamples;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getLanguageName(File sampleFile) {
		String languageName = null;

		if (sampleFile == null || sampleFile.isDirectory()) {
			LOGGER.info(MSG_INVALID_LANGUAGE_SAMPLE_FILE_IGNORED);
		} else if (sampleFile.isFile()) {
			languageName = sampleFile.getName().split("\\.")[0];
		}

		return languageName;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> getWords(File sampleFile) {
		Scanner sFile = null;
		List<String> words = new ArrayList<String>();
		if (sampleFile == null) {
			LOGGER.info(MSG_INVALID_LANGUAGE_SAMPLE_FILE_IGNORED);
			return words;
		}
		try {
			sFile = new Scanner(sampleFile);
			sFile.useDelimiter(regexWithPunctuation());
			while (sFile.hasNext()) {
				String word = sFile.next(); 
				if (StringUtils.isNotBlank(word)) {
					words.add(word.toLowerCase());
				}
			}
		} catch (FileNotFoundException e) {
			LOGGER.info(MSG_INVALID_LANGUAGE_SAMPLE_FILE_IGNORED, e);
		} finally {
			IOUtils.closeQuietly(sFile);
		}
		return words;
	}

	/**
	 * {@inheritDoc}
	 */
	public String removePunctuation(String word) {

		char[] punctuationChars = ApplicationProperties.getPunctuationChars();
		if (ArrayUtils.isEmpty(punctuationChars) || StringUtils.isBlank(word)) {
			return word;
		}
		String returnVal = word;
		for (char punctuationChar : punctuationChars) {
			returnVal = returnVal.replaceAll(Pattern.quote("" + punctuationChar), "");
		}

		return returnVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public String regexWithPunctuation() {

		String regex = "\\s";
		char[] punctuationChars = ApplicationProperties.getPunctuationChars();

		for (char punctuationChar : punctuationChars) {
			regex = regex + "|\\" + punctuationChar;
		}

		return regex;

	}

	/**
	 * {@inheritDoc}
	 */
	public List<LanguageSamples> loadLangSamples(File langSamplesFilePath) {
		List<LanguageSamples> langSampleFiles = null;
		IOFileFilter filter = new WildcardFileFilter("*");

		for (File langSampleFile : FileUtils.listFiles((langSamplesFilePath), filter, null)) {
			langSampleFiles = addLangSample(langSampleFiles, langSampleFile);
		}

		return langSampleFiles;
	}

}
