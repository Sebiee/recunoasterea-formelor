package lab5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Localitate {
	private double x, y;
	@SuppressWarnings("unused")
	private String nume;
	private String judet;

	public Localitate(double x, double y) {
		this.x = x;
		this.y = y;
		this.nume = "Unnamed place";
		this.judet = "Unnamed County";
	}

	public Localitate(double x, double y, String nume) {
		this.x = x;
		this.y = y;
		this.nume = nume;
	}

	public Localitate(String[] readPlace) {
		this.x = Double.valueOf(readPlace[0]);
		this.y = Double.valueOf(readPlace[1]);
		this.nume = readPlace[2];
		this.judet = readPlace[3];
	}

	public double getEuclideanDistance(Localitate l) {
		return Math.sqrt(Math.pow(x - l.x, 2) + Math.pow(y - l.y, 2));
	}

	public void assignCountyBasedOnKPlaces(Localitate[] places, int k) {
		// TODO Auto-generated method stub
		Localitate[] searchPlaces = places.clone();

		Arrays.sort(searchPlaces, (a, b) -> Double.compare(getEuclideanDistance(a), getEuclideanDistance(b)));

		this.judet = Localitate.getMostFrequentCountyOfFirstKPlaces(searchPlaces, k);

	}

	private static String getMostFrequentCountyOfFirstKPlaces(Localitate[] places, int k) {
		Integer max = 1;
		String county = places[0].getCounty();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < k; i++) {
			String newCounty = places[i].getCounty();
			map.put(newCounty, map.getOrDefault(newCounty, 0) + 1);
			if (map.get(newCounty) > max) {
				max = map.get(newCounty);
				county = newCounty;
			}
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.print(entry.getKey() + entry.getValue());
			System.out.println();
		}
		return county;
	}

	public String getCounty() {
		return judet;
	}

	public int getMaxKForDesiredAccuracy(Localitate[] places, double desiredAccuracy) {
		// TODO Auto-generated method stub
		Localitate[] searchPlaces = places.clone();

		Arrays.sort(searchPlaces, (a, b) -> Double.compare(getEuclideanDistance(a), getEuclideanDistance(b)));
		double currentAccuracy = 1;
		int k = -1;
		while (currentAccuracy >= desiredAccuracy) {
			k++;
			currentAccuracy = getHighestCountyFrequencyPercentageOfFirstKPlaces(searchPlaces, k);
		}
		return k - 1;
	}

	private double getHighestCountyFrequencyPercentageOfFirstKPlaces(Localitate[] places, int k) {
		int suma = 0, max = 1;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < k; i++) {
			String newCounty = places[i].getCounty();
			map.put(newCounty, map.getOrDefault(newCounty, 0) + 1);
			if (map.get(newCounty) > max) {
				max = map.get(newCounty);
			}
			suma++;
		}
//		System.out.println(k);
//		for (Map.Entry<String, Integer> entry : map.entrySet()) {
//			System.out.print(entry.getKey() + entry.getValue());
//			System.out.println();
//		}
//		System.out.println(max + " - " + suma);
		return (double) max / (suma != 0 ? suma : 1);
	}

}
