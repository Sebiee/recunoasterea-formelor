package ro.usv.rf;

public class MainClass {

	public static void main(String[] args) {
		double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
		FileUtils.writeLearningSetToFile("out.csv", learningSet);
		FileUtils.writeLearningSetToFile("out-normalized.csv", normalizeLearningSet(learningSet));
	}

	private static double[][] normalizeLearningSet(double[][] learningSet) {
		double[][] normalizedLearningSet = new double[learningSet.length][];

		double[][] minMaxOnCol = getMinMaxOnCols(learningSet);
		for (int i = 0; i < minMaxOnCol.length; i++) {
			for (int j = 0; j < minMaxOnCol[i].length; j++) {
				System.out.print(minMaxOnCol[i][j] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < normalizedLearningSet.length; i++) {
			normalizedLearningSet[i] = new double[learningSet[i].length];
			for (int j = 0; j < normalizedLearningSet[i].length; j++) {
				
				normalizedLearningSet[i][j] =
						(learningSet[i][j] - minMaxOnCol[j][1])
						/(minMaxOnCol[j][0] - minMaxOnCol[j][1]);
				normalizedLearningSet[i][j] = (double)
						Math.round(normalizedLearningSet[i][j] * 100) / 100;
			}
		}
		
		return normalizedLearningSet;
	}
	
	private static double[][] getMinMaxOnCols(double[][] learningSet) {
		double[][] minMaxOnCol = new double[learningSet[0].length][2];

		for (int i = 0; i < minMaxOnCol.length; i++) {
			minMaxOnCol[i][0] = Double.MIN_VALUE;
			minMaxOnCol[i][1] = Double.MAX_VALUE;
			for (int j = 0; j < learningSet.length; j++) {
				if(learningSet[j][i] > minMaxOnCol[i][0]) {
					minMaxOnCol[i][0] = learningSet[j][i];
				}
				if(learningSet[j][i] < minMaxOnCol[i][1]) {
					minMaxOnCol[i][1] = learningSet[j][i];
				}
			}
		}
		
		return minMaxOnCol;
	}
}
