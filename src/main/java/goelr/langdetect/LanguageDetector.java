package goelr.langdetect;

import java.io.FileNotFoundException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import goelr.langdetect.helper.LanguageHelperImpl;

/**
 * command line utility to run language detector
 * @author goelr
 *
 */
public class LanguageDetector {

	private static Logger LOGGER = LoggerFactory.getLogger(LanguageDetector.class);
	
	public static void main(String[] args) {

		String langFile = null;
		try {
			PropertyConfigurator.configure(DetectorServiceImpl.class.getResource("resources/log4j.properties"));
			Options options = new Options();
			options.addOption("f", true, "filename (with path) to detect language.");
			options.addOption("s", true, "path for language sample files.");
			CommandLineParser parser = new DefaultParser();
			HelpFormatter formatter = new HelpFormatter();

			CommandLine line = parser.parse(options, args);
			langFile = line.getOptionValue("f");
			String sampleFilesPath = line.getOptionValue("s");

			LOGGER.info("***** Starting Language detection for " + langFile + " *****");
			
			if (StringUtils.isBlank(langFile) || StringUtils.isBlank(sampleFilesPath)) {
				formatter.printHelp("java LanguageServiceImpl", options, true);
				return;
			}

			DetectorService langService = new DetectorServiceImpl();
			langService.setDetector(new DetectorImpl());
			langService.setLanguageHelper(new LanguageHelperImpl());
			
			langService.detectLanguage(langFile, sampleFilesPath);
			LOGGER.info("***** Finished Language detection. *****");

		} catch (ParseException e) {
			LOGGER.error("Parsing failed for arguments. " + e.getMessage());
		} catch (FileNotFoundException e) {
			LOGGER.error(langFile + "-" + e.getMessage());
		}

	}
}
