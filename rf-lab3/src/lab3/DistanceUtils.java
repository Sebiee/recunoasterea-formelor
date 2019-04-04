package lab3;

public class DistanceUtils {

	public static double getEuclideanDistance(double[] e1, double[] e2) {
		double euclideanDistance = 0;

		euclideanDistance = Math.pow(e1[0] - e2[0], 2) + Math.pow(e1[1] - e2[1], 2);

		return Math.sqrt(euclideanDistance);
	}

	public static double[] getEuclideanDistanceBetweenFirstCoordinateAndTheRest(double[][] patterns) {
		double[] eDistance = new double[patterns.length];
		for (int i = 1; i < patterns.length; i++) {
			eDistance[i] = DistanceUtils.getEuclideanDistance(patterns[0], patterns[i]);
		}
		return eDistance;
	}

	public static double getMahalanobisDistance(double[] e1, double[] e2, int n) {
		double mahalanobisDistance = 0;

		for (int j = 0; j < e1.length; j++) {
			mahalanobisDistance += Math.pow(e1[j] - e2[j], n);
		}

		return Math.pow(mahalanobisDistance, 1 / n);
	}

	public static double[] getMahalanobisDistanceBetweenFirstPatternAndTheRest(double[][] patterns) {
		double[] mDistance = new double[patterns.length];
		mDistance[0] = 0;

		for (int i = 1; i < patterns.length; i++) {
			mDistance[i] = DistanceUtils.getMahalanobisDistance(patterns[0], patterns[i], patterns.length);
		}

		return mDistance;
	}

	public static double getCebisevDistance(double[] e1, double[] e2) {
		double cebisevDistance = 0;

		for (int i = 0; i < e1.length; i++) {
			if (Math.abs(e1[i] - e2[i]) > cebisevDistance) {
				cebisevDistance = Math.abs(e1[i] - e2[i]);
			}
		}

		return cebisevDistance;
	}

	public static double[] getCebisevDistanceBetweenFirstPatternAndTheRest(double[][] patterns) {
		double[] cDistance = new double[patterns.length];
		cDistance[0] = 0;

		for (int i = 0; i < patterns.length; i++) {
			cDistance[i] = getCebisevDistance(patterns[0], patterns[i]);
		}

		return cDistance;
	}

	public static double getCityBlockDistance(double[] e1, double[] e2) {
		double cbDistance = 0;

		for (int i = 0; i < e1.length; i++) {
			cbDistance += Math.abs(e1[i] - e2[i]);
		}

		return cbDistance;
	}

	public static double[] getCityBlockDistanceBetweenFirstPatternAndTheRest(double[][] patterns) {
		double[] cbDistance = new double[patterns.length];

		for (int i = 0; i < patterns.length; i++) {
			cbDistance[i] = DistanceUtils.getCityBlockDistance(patterns[0], patterns[i]);
		}

		return cbDistance;
	}
}
