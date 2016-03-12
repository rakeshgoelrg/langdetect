package goelr.langdetect.model;

import java.text.DecimalFormat;

/**
 * @author goelr
 * result for a language detection
 */
public class DetectionResult implements Comparable<DetectionResult> {

	private String languageName;
	private long matchingCount;
	private long totalCount;
	
	public String getLanguageName() {
		return languageName;
	}
	
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public long getMatchingCount() {
		return matchingCount;
	}

	public void setMatchingCount(long matchingCount) {
		this.matchingCount = matchingCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public double getMatchingPercentage() {
		if (totalCount > 0 ) {
		return (matchingCount * 100.0) / totalCount;
		}
		return 0.0;
	}
	
	public String getFormatedMatchingPercentage() {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(getMatchingPercentage());
	}

	public int compareTo(DetectionResult other) {
		return Double.compare(this.getMatchingPercentage(), 
				other.getMatchingPercentage());
	}

}
