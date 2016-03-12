package goelr.langdetect.helper;

import java.io.File;
import java.util.List;

import goelr.langdetect.model.LanguageSamples;

/**
 * manage language sample files 
 * @author goelr
 *
 */
public interface LanguageHelper {

	/**
	 * Load language sample file names
	 * @param filePath customized path for sample files
	 * @return list list of sample file names
	 */
	List<LanguageSamples> loadLangSamples(File filePath);
	
	/**
	 * add a new sample file to mem list of lang files
	 * @param allSamples list of all lang files
	 * @param newSample new file
	 * @return updated list
	 */
	List<LanguageSamples> addLangSample(List<LanguageSamples> allSamples, File newSample);
	
	/**
	 * add a new sample file to mem list of lang files
	 * @param allSamples list of all lang files
	 * @param newSample new file
	 * @param languageName  language Name
	 * @return updated list
	 */
	List<LanguageSamples> addLangSample(List<LanguageSamples> allSamples, File newSample, String languageName);
	
	/**
	 * get language name from sample file
	 * @param sampleFile sample file
	 * @return lang name
	 */
	String getLanguageName(File sampleFile);
	
	/**
	 * get words from sample file
	 * @param sampleFile sample file
	 * @return words in file
	 */
	List<String> getWords(File sampleFile);
	
	/**
	 * remove punctuation chars
	 * @param word word
	 * @return word without punctuation chars
	 */
	String removePunctuation(String word);
	
	/**
	 * get regex for punctuation chars from properties
	 * @return regex
	 */
	String regexWithPunctuation();
}
