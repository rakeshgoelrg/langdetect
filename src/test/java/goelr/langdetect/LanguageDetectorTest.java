package goelr.langdetect;

import org.junit.Test;

import goelr.langdetect.LanguageDetector;

public class LanguageDetectorTest {
	

	@Test
	public void testMain() {
		String[] args = new String[] {
			"-f",
			"D:\\Development\\workspace\\langdetect\\UNKNOWN.txt",
			"-s",
			"D:\\Development\\workspace\\langdetect\\samples" 
		};
		
		LanguageDetector.main(args);
		
	}

}
