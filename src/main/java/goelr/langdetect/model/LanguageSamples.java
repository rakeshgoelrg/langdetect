/**
 * Model to load language sample
 */
package goelr.langdetect.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author goelr
 *
 */
public class LanguageSamples {

	private String languageName;
	private List<File> samples;
	
	public LanguageSamples(String languageName, File sample) {
		this.languageName = languageName;
		this.samples = new ArrayList<File>();
		this.samples.add(sample);
	}
	
	public String getLanguageName() {
		return languageName;
	}
	
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public List<File> getSamples() {
		return samples;
	}

	public void setSamples(List<File> samples) {
		this.samples = samples;
	}
	
}
