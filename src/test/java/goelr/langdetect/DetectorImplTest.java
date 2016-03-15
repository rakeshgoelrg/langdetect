package goelr.langdetect;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import goelr.langdetect.Detector;
import goelr.langdetect.DetectorImpl;
import goelr.langdetect.helper.LanguageHelper;
import goelr.langdetect.helper.LanguageHelperImpl;
import goelr.langdetect.model.DetectionResult;
import goelr.langdetect.model.LanguageSamples;

public class DetectorImplTest {

	private File langSamplesTestDir, langTestFile;
	private Detector detector;
	private LanguageHelper languageHelper;
	private LanguageSamples langSamples;
	private static final URL TEST_SAMPLE_TEST_DIRPATH = DetectorServiceImplTest.class.getResource("resources/samples/");
	private static final URL TEST_UNKNOWN_FILE = DetectorServiceImplTest.class.getResource("resources/test/UNKNOWN.txt");

	@Before
	public void setUp() throws Exception {
		langSamplesTestDir = new File(TEST_SAMPLE_TEST_DIRPATH.toURI());
		langTestFile = new File(TEST_UNKNOWN_FILE.toURI()); 
		detector = new DetectorImpl();
		languageHelper = new LanguageHelperImpl();
		langSamples = languageHelper.loadLangSamples(langSamplesTestDir).get(1);
	}

	@Test
	public void testDetectLanguage() {
		DetectionResult result = detector.detectLanguage(langSamples, langTestFile, languageHelper);
		assertEquals("ENGLISH", result.getLanguageName());
	}

}
