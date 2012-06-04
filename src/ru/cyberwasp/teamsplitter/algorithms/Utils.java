package ru.cyberwasp.teamsplitter.algorithms;

public class Utils {

	public static double calcAvg(double[] data){
		double res = 0.0;
		for (int i = 0; i < data.length; i++) {
			res += data[i];
		}
		return res / data.length;
	}
	
	public static double calcSquareOfStandartDeviation(double[] data){
		double res = 0.0;
		double mediane = calcAvg(data);
		for (int i = 0; i < data.length; i++) {
			double diff = data[i] - mediane;
			res += diff * diff;
		}
		return res / data.length;
	}
	
}
