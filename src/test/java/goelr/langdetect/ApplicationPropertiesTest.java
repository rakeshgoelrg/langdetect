package goelr.langdetect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import goelr.langdetect.ApplicationProperties;

public class ApplicationPropertiesTest extends BaseTest {

	@Test
	public void testGetPunctuationChars() {
		char[] chars = ApplicationProperties.getPunctuationChars();
		assertNotNull(chars);
		assertTrue(ArrayUtils.contains(chars, '.'));
	}
	
	@Test
	public void testGetSamplesDirPath() {
		String path = ApplicationProperties.getSamplesDirPath();
		assertNotNull(path);
		assertEquals("resources/samples", path);
	}
	
	@Test
	public void testGetSampleDirPathFile() throws URISyntaxException {
		String path = ApplicationProperties.getSamplesDirPathFile().getPath();
		assertNotNull(path);
		assertTrue(path.endsWith("goelr\\langdetect\\resources\\samples"));
	}
}
