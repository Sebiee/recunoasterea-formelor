package lab3;

public class MainClass {

	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patterns and %s features", numberOfPatterns,
					numberOfFeatures));

			int i = 0;
			for (double euclideanDistance : DistanceUtils
					.getEuclideanDistanceBetweenFirstCoordinateAndTheRest(learningSet)) {
				System.out.println("Euclidean distance beetween 0 and " + i++ + ": " + euclideanDistance);
			}
			i = 0;

			for (double mahalanobisDistance : DistanceUtils
					.getMahalanobisDistanceBetweenFirstPatternAndTheRest(learningSet)) {
				System.out.println("Mahalanobis distance beetween 0 and " + i++ + ": " + mahalanobisDistance);
			}
			i = 0;

			for (double cebisevDistance : DistanceUtils.getCebisevDistanceBetweenFirstPatternAndTheRest(learningSet)) {
				System.out.println("Cebisev distance beetween 0 and " + i++ + ": " + cebisevDistance);
			}
			i = 0;

			for (double cityBlockDistance : DistanceUtils
					.getCityBlockDistanceBetweenFirstPatternAndTheRest(learningSet)) {
				System.out.println("CityBlock distance beetween 0 and " + i++ + ": " + cityBlockDistance);
			}
			i = 0;
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
