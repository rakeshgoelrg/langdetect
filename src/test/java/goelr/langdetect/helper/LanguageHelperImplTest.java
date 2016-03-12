/**
 * 
 */
package goelr.langdetect.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import goelr.langdetect.BaseTest;
import goelr.langdetect.helper.LanguageHelper;
import goelr.langdetect.helper.LanguageHelperImpl;
import goelr.langdetect.model.LanguageSamples;

/**
 * @author goelr
 *
 */
public class LanguageHelperImplTest extends BaseTest {

	LanguageHelper languageHelper = new LanguageHelperImpl();
	File langSampleFile, langSampleTestDir;
	private static final URL TEST_SAMPLE_FILE = LanguageHelperImplTest.class.getResource("..\\resources\\samples\\ENGLISH.1.txt");
	private static final URL TEST_SAMPLE_FILE1 = LanguageHelperImplTest.class.getResource("..\\resources\\samples\\ENGLISH.2");
	private static final URL TEST_SAMPLE_FILE2 = LanguageHelperImplTest.class.getResource("..\\resources\\samples\\ENGLISH.txt");
	private static final URL TEST_SAMPLE_FILE3 = LanguageHelperImplTest.class.getResource("..\\resources\\samples\\DUTCH");
	private static final URL TEST_SAMPLE_TEST_DIRPATH = LanguageHelperImplTest.class.getResource("..\\resources\\samples\\");
	

	@Before
	public void setUp() throws Exception {
		langSampleFile = new File(TEST_SAMPLE_FILE.toURI());
		langSampleTestDir = new File(TEST_SAMPLE_TEST_DIRPATH.toURI());
	}

	/**
	 * Test method for {@link goelr.langdetect.helper.LanguageHelperImpl#addLangSample(java.util.List, java.io.File)}.
	 * @throws URISyntaxException 
	 */
	@Test
	public void testAddSampleListOfLanguageSamplesFile() throws URISyntaxException {
		List<LanguageSamples> langSamples = languageHelper.addLangSample(null, langSampleFile);
		
		assertNotNull(langSamples);
		assertEquals("ENGLISH", langSamples.get(0).getLanguageName());
		assertEquals(langSampleFile, langSamples.get(0).getSamples().get(0));
		assertEquals(1,  langSamples.size());
		
		langSampleFile = new File(TEST_SAMPLE_FILE2.toURI());
		langSamples = languageHelper.addLangSample(langSamples, langSampleFile);
		assertNotNull(langSamples);
		assertEquals("ENGLISH", langSamples.get(0).getLanguageName());
		assertEquals(langSampleFile, langSamples.get(0).getSamples().get(1));
		assertEquals(1,  langSamples.size());
		
		langSampleFile = new File(TEST_SAMPLE_FILE3.toURI());
		langSamples = languageHelper.addLangSample(langSamples, langSampleFile);
		assertNotNull(langSamples);
		assertEquals(2,  langSamples.size());
		assertEquals("ENGLISH", langSamples.get(0).getLanguageName());
		assertEquals(2, langSamples.get(0).getSamples().size());
		assertEquals("DUTCH", langSamples.get(1).getLanguageName());
		assertEquals(langSampleFile, langSamples.get(1).getSamples().get(0));
		
		langSamples = languageHelper.addLangSample(null, langSampleTestDir);
		assertNull(langSamples);
	}


	/**
	 * Test method for {@link goelr.langdetect.helper.LanguageHelperImpl#getLanguageName(java.io.File)}.
	 * @throws URISyntaxException 
	 */
	@Test
	public void testGetLanguageName() throws URISyntaxException {
		
		assertNull(languageHelper.getLanguageName(null));
		
		
		assertEquals("ENGLISH", languageHelper.getLanguageName(langSampleFile));
		
		langSampleFile = new File(TEST_SAMPLE_FILE1.toURI());
		assertEquals("ENGLISH", languageHelper.getLanguageName(langSampleFile));
		
		langSampleFile = new File(TEST_SAMPLE_FILE2.toURI());
		assertEquals("ENGLISH", languageHelper.getLanguageName(langSampleFile));
		
		langSampleFile = new File(TEST_SAMPLE_FILE3.toURI());
		assertEquals("DUTCH", languageHelper.getLanguageName(langSampleFile));
		
		assertNull(languageHelper.getLanguageName(langSampleTestDir));
	}

	/**
	 * Test method for {@link goelr.langdetect.helper.LanguageHelperImpl#getWords(java.io.File)}.
	 */
	@Test
	public void testGetWords() {
		List<String> words = languageHelper.getWords(langSampleFile);
		
		assertNotNull(words);
		assertEquals(31, words.size());
		
		words = languageHelper.getWords(null);
	}
	
	@Test
	public void testRemovePunctuation() {
		assertEquals("testing", languageHelper.removePunctuation("testing,"));
		assertEquals("testing", languageHelper.removePunctuation(".testing"));
		assertEquals("testing", languageHelper.removePunctuation("testing:"));
		assertEquals("testing", languageHelper.removePunctuation(".testing;;"));
		
		assertEquals(null, languageHelper.removePunctuation(null));
		assertEquals("", languageHelper.removePunctuation(""));
		assertEquals("  ", languageHelper.removePunctuation("  "));
	}
	
	@Test
	public void testRegexWithPunctuation() {
		assertEquals("\\s|\\.|\\,|\\:|\\;", languageHelper.regexWithPunctuation());
	}
	
	@Test
	public void testLoadSamplesFile() throws URISyntaxException {
		
		List<LanguageSamples> langSamples = languageHelper.loadLangSamples(langSampleTestDir);
		
		assertNotNull(langSamples);
		assertEquals(2,  langSamples.size());
		assertEquals("DUTCH", langSamples.get(0).getLanguageName());
		assertEquals(1, langSamples.get(0).getSamples().size());
		assertEquals("ENGLISH", langSamples.get(1).getLanguageName());
		assertEquals(3, langSamples.get(1).getSamples().size());
		
	}
	
}
