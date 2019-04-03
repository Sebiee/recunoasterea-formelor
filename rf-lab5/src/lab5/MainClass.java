package lab5;

public class MainClass {

	public static void main(String[] args) {
		String[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
		Localitate[] newLoc = new Localitate[3];
		newLoc[0] = new Localitate(22.78, 45.8);
		newLoc[1] = new Localitate(24, 45.15);
		newLoc[2] = new Localitate(25.33, 45.44);

		Localitate[] places = new Localitate[learningSet.length];

		for (int i = 0; i < learningSet.length; i++) {
			places[i] = new Localitate(learningSet[i]);
		}

		for (int k : new Integer[] { 9, 11, 13, 17, 31 }) {
			System.out.println(k + "-NN:");
			newLoc[0].assignCountyBasedOnKPlaces(places, k);
			newLoc[1].assignCountyBasedOnKPlaces(places, k);
			newLoc[2].assignCountyBasedOnKPlaces(places, k);

			for (int i = 0; i < newLoc.length; i++) {
				System.out.println("->Localitatea " + i + " se afla in judetul " + newLoc[i].getCounty());
			}
			System.out.println();
		}

		for (int i = 0; i < newLoc.length; i++) {
			System.out.println("->Max K pentru " + i + ": " + newLoc[i].getMaxKForDesiredAccuracy(places, 0.9));
		}
		System.out.println();
	}

}
