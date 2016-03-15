package goelr.langdetect;

import org.junit.Test;

import goelr.langdetect.LanguageDetector;

public class LanguageDetectorTest {
	

	@Test
	public void testMain() {
		String[] args = new String[] {
			"-f",
			"/Users/buddy/development/langdetect/UNKNOWN.txt",
			"-s",
			"/Users/buddy/development/langdetect/samples"
		};
		
		LanguageDetector.main(args);
		
	}

}
