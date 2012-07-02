package ru.cyberwasp.teamsplitter.algorithms;

public class Utils {

    public static double calcAvg(double[] data) {
        double res = 0.0;
        for (double d : data) {
            res += d;
        }
        return res / data.length;
    }

    public static double calcSquareOfStandardDeviation(double[] data) {
        double res = 0.0;
        double avg = calcAvg(data);
        for (double d : data) {
            double diff = d - avg;
            res += diff * diff;
        }
        return res / data.length;
    }

}
