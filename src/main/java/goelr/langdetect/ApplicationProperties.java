package goelr.langdetect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application property helper
 * @author goelr
 *
 */
public final class ApplicationProperties {

	private static Logger LOGGER = LoggerFactory.getLogger(ApplicationProperties.class);
	private transient static Properties props = null;
	
	private static final String PUNCTUATION_CHARS_DEFAULT = ".,:;";
	private static final String PUNCTUATION_CHARS_KEY = "punctuation-chars";
	
	private static final String LANG_SAMPLES_DIRPATH_DEFAULT = "resources\\samples";
	private static final String LANG_SAMPLES_DIRPATH_KEY = "samples.source.dir";
	
	private ApplicationProperties() {
		//utility class
	}
	
	public static String getProperty(String key, String defaultValue) {
		loadProperties();
		if (props == null) {
			return defaultValue;
		}
		String propValue = props.getProperty(key);
		return propValue;
	}
	
	private static void loadProperties() {
		if (props == null) {

			InputStream inStream = ApplicationProperties.class.getResourceAsStream("resources/Application.properties");
			props = new Properties();

			try {
				props.load(inStream);
			} catch (IOException e) {
				props = null;
				LOGGER.error("Unable to properties file", e);
			}
		}
	}
	
	/**
	 * @return Punctuation chars
	 */
	public static char[] getPunctuationChars() {
		char[] punctuationChars = getProperty(PUNCTUATION_CHARS_KEY, 
				PUNCTUATION_CHARS_DEFAULT).toCharArray();
		return punctuationChars;
	}
	
	/**
	 * get sample files relative path configured
	 * @return sample files relative path
	 */
	public static String getSamplesDirPath() {
		return getProperty(LANG_SAMPLES_DIRPATH_KEY, LANG_SAMPLES_DIRPATH_DEFAULT);
	}
	
	/**
	 * get sample files resource path
	 * @return absolute sample files resource path
	 * @throws URISyntaxException 
	 */
	public static File getSamplesDirPathFile() throws URISyntaxException {
		URL resourcePath = ApplicationProperties.class.getResource(getSamplesDirPath());
		return (new File(resourcePath.toURI()));
	}
}
