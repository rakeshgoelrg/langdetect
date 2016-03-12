/**
 * 
 */
package goelr.langdetect;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import goelr.langdetect.Detector;
import goelr.langdetect.DetectorService;
import goelr.langdetect.DetectorServiceImpl;
import goelr.langdetect.helper.LanguageHelper;
import goelr.langdetect.model.DetectionResult;
import goelr.langdetect.model.LanguageSamples;

/**
 * @author goelr
 *
 */
public class DetectorServiceImplTest extends BaseTest {

	private LanguageHelper languageHelper;
	private Detector detector;
	private DetectorService languageService;
	private File langSampleTestDir, langTestFile;
	DetectionResult result;
	private static final URL TEST_LANG_SAMPLES_DIRPATH = DetectorServiceImplTest.class.getResource("resources\\samples\\");
	private static final URL TEST_UNKNOWN_FILE = DetectorServiceImplTest.class.getResource("resources\\test\\UNKNOWN.txt");
	
	@Before
	public void setUp() throws Exception {
		languageHelper = Mockito.mock(LanguageHelper.class);
		detector = Mockito.mock(Detector.class);
		languageService = new DetectorServiceImpl();
		languageService.setDetector(detector);
		languageService.setLanguageHelper(languageHelper);
		
		langSampleTestDir = new File(TEST_LANG_SAMPLES_DIRPATH.toURI());
		langTestFile = new File(TEST_UNKNOWN_FILE.toURI()); 
		
		result = new DetectionResult();
		result.setLanguageName("ENGLISH");
		result.setMatchingCount(5);
		result.setTotalCount(10);
		
		Mockito.when(detector.detectLanguage(Mockito.any(LanguageSamples.class),
				Mockito.any(File.class), Mockito.any(LanguageHelper.class))).thenReturn(result);
	}

	@Test
	public void testDetectLanguageStringString() throws FileNotFoundException {
		
		try {
			languageService.detectLanguage("abc.txt", "samples");
			fail();
		} catch(FileNotFoundException e) {
			// do nothing
		}
		
		try {
			
			languageService.detectLanguage(langTestFile.getAbsolutePath(), null);
			fail();
		} catch(FileNotFoundException e) {
			// do nothing
		} catch(NullPointerException e) {
			// do nothing
		}
		
		try {
			languageService.detectLanguage(null, langSampleTestDir.getAbsolutePath());
			fail();
		} catch(FileNotFoundException e) {
			// do nothing
		} catch(NullPointerException e) {
			// do nothing
		}
	}

}
