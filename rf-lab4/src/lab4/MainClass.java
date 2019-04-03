package lab4;

public class MainClass {

	public static void main(String[] args) {
		double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
		FileUtils.writeLearningSetToFile("out.csv", learningSet);
		FileUtils.writeLearningSetToFile("out-normalized.csv", matchSet(learningSet));
	}

	private static double[][] matchSet(double[][] learningSet) {
		double[][] nlSet = new double[learningSet.length][];

		for (int i = 0; i < nlSet.length; i++) {
			if (i != 4)
				nlSet[i] = new double[learningSet[i].length - 1];
			else
				nlSet[i] = new double[learningSet[i].length];
			for (int j = 0; j < nlSet[i].length; j++) {
				nlSet[i][j] = learningSet[i][j];
			}
		}
		for (int i = 0; i < nlSet.length; i++) {
			for (int j = 0; j < nlSet[i].length; j++) {
				System.out.print(nlSet[i][j] + " ");
			}
			System.out.println();
		}

		double[][] minMaxOnCol = getMinMaxOnCols(nlSet);
		double[][] distMat = getDistMat(nlSet);
		for (int i = 0; i < distMat.length; i++) {
			for (int j = 0; j < distMat[i].length; j++) {
				System.out.print(distMat[i][j] + " ");
			}
			System.out.println();
		}

		int spi = learningSet.length - 1;
		double[] dist = distSet[spi];
		int cpi = 0;
		double cpd = distSet[cpi];
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] < cpd && cpi != i) {
				cpd = dist[i];
				cpi = i;
			}
		}

//		learnin

//		for (int i = 0; i < nlSet.length; i++) {
//			nlSet[i] = new double[learningSet[i].length - 1];
//			for (int j = 0; j < nlSet[i].length; j++) {
//
//				nlSet[i][j] = (learningSet[i][j] - minMaxOnCol[j][1]) / (minMaxOnCol[j][0] - minMaxOnCol[j][1]);
//				nlSet[i][j] = (double) Math.round(nlSet[i][j] * 100) / 100;
//			}
//		}

		return nlSet;
	}

	private static double[][] getDistMat(double[][] set) {
		double[][] distSet = new double[set.length][];
		double sum = 0;
		for (int i = 0; i < set.length; i++) {
			distSet[i] = new double[set.length];
		}
		for (int i = 0; i < set.length; i++) {
			for (int j = i + 1; j < set.length; j++) {
				sum = 0;
				for (int k = 0; k < set[i].length; k++) {
					sum = sum + Math.pow((set[i][k] - set[j][k]), 2);
				}
				distSet[i][j] = Math.sqrt(sum);
				distSet[j][i] = distSet[i][j];
			}
		}
		return distSet;
	}

	private static double[][] getMinMaxOnCols(double[][] learningSet) {
		double[][] minMaxOnCol = new double[learningSet[0].length][2];

		for (int i = 0; i < minMaxOnCol.length; i++) {
			minMaxOnCol[i][0] = Double.MIN_VALUE;
			minMaxOnCol[i][1] = Double.MAX_VALUE;
			for (int j = 0; j < learningSet.length; j++) {
				if (learningSet[j][i] > minMaxOnCol[i][0]) {
					minMaxOnCol[i][0] = learningSet[j][i];
				}
				if (learningSet[j][i] < minMaxOnCol[i][1]) {
					minMaxOnCol[i][1] = learningSet[j][i];
				}
			}
		}

		return minMaxOnCol;
	}
}