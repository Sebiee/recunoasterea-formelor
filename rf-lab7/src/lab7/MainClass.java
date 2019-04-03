package lab7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<Pattern>> distinctClass = new HashMap<>();
		String[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
		for (int i = 0; i < learningSet.length; i++) {
			String classOfPattern = learningSet[i][learningSet[i].length - 1];
			distinctClass.put(classOfPattern, distinctClass.getOrDefault(classOfPattern, new ArrayList<Pattern>()));
			distinctClass.get(classOfPattern).add(new Pattern(learningSet[i]));
		}
		Double[][] w = new Double[distinctClass.size()][];
		int i = 0, j = 0;

		for (Map.Entry<String, ArrayList<Pattern>> entry : distinctClass.entrySet()) {
			w[i] = new Double[entry.getValue().size() + 1];
			Double sum = 0.0;
			for (Pattern p : entry.getValue()) {
				w[i][j] = p.getAvg();
				sum += Math.pow(w[i][j], 2);
			}
			w[i][w[i].length - 1] = -0.5 * sum;
		}
		Double[] newPattern = new Double[] { 4.0, 5.0, 1.0 };
		Double[] psi = new Double[distinctClass.size()];
		Double max = Double.MIN_VALUE;
		int idx = -1;
		for (i = 0; i < psi.length; i++) {
			psi[i] = multiply(w[i], newPattern);
			if (psi[i] > max) {
				max = psi[i];
				idx = i;
			}
		}
		System.out.println("Clasa noului pattern este " + learningSet[idx][2]);
	}

	private static Double multiply(Double[] doubles, Double[] newPattern) {
		Double sum = 0.0;
		for (int i = 0; i < newPattern.length; i++) {
			sum += (doubles[i] * newPattern[i]);
		}
		return sum;
	}

}
