package lab7;

public class Pattern {
	private Double[] features;
	private String group;

	public Pattern(String[] pattern) {
		features = new Double[pattern.length - 1];
		group = pattern[pattern.length - 1];

		for (int i = 0; i < pattern.length - 1; i++) {
			features[i] = Double.valueOf(pattern[i]);
		}
	}

	public Double getAvg() {
		Double sum = 0.0;
		for (Double feature : features) {
			sum += feature;
		}
		return sum / features.length;
	}
}
