/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PSEVO tochka
 */
public class NumberUtils {

    public static List<Double> arrayToList(double[] arr) {
        List<Double> res = new ArrayList<>();
        for (int k = 0; k < arr.length; k++) {
            res.add(arr[k]);
        }
        return res;
    }

    public static List<Double> excludefromList(List<Double> baseList, List<Double> exclusionList) {
        List<Double> res = new ArrayList<>();
        for (int k = 0; k < baseList.size(); k++) {
            if (!exclusionList.contains(baseList.get(k))) {
                res.add(baseList.get(k));
            }
        }
        return res;
    }

    public static double[] excludefromList(double[] base, double[] exclusion) {
        List<Double> baseList = arrayToList(base);
        List<Double> exclusionList = arrayToList(exclusion);
        List<Double> resList = excludefromList(baseList, exclusionList);
        double[] res = new double[resList.size()];
        for (int k = 0; k < resList.size(); k++) {
            res[k] = resList.get(k);
        }
        return res;
    }

    public static double[] excludefromList(double[] base, List<Double> exclusionList) {
        List<Double> baseList = arrayToList(base);
        List<Double> resList = excludefromList(baseList, exclusionList);
        double[] res = new double[resList.size()];
        for (int k = 0; k < resList.size(); k++) {
            res[k] = resList.get(k);
        }
        return res;
    }

    public static void outputForPrintDoubleList(List<Double> baseList) {
        System.out.println("Double List " + baseList.getClass().getSimpleName() + ". size: " + baseList.size());
        for (int k = 0; k < baseList.size(); k++) {
            System.out.println(k + ": " + baseList.get(k));
        }
    }

    public static double[] copyArrayToArray(double[] source) {
        double[] res = new double[source.length];
        for (int k = 0; k < source.length; k++) {
            res[k] = source[k];
        }
        return res;
    }

    public static void outputForPrintDoubleArray(double[] base) {
        System.out.println("Double Array " + base.getClass().getSimpleName() + ". length " + base.length);
        for (int k = 0; k < base.length; k++) {
            System.out.println(k + ": " + base[k]);
        }
    }

    /**
     * Generates an double array with a given min and max values and a fixed
     * delta=step between any neighbors
     *
     * @param minVal
     * @param maxVal
     * @param step
     * @return
     */
    public static double[] generateDoubleArray(double minVal, double maxVal, double step) {
        int dim = 1;
        if (step > 0) {
            dim = (int) (Math.abs(minVal - maxVal) / step) + 1;
        }
        double[] res = new double[dim];
        res[0] = minVal;
        for (int k = 1; k < dim; k++) {
            res[k] = res[k - 1] + step;
        }
        return res;
    }

    public static String genRandomSuperBigInt() {
        String res = ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";
        res += ((int) (Integer.MAX_VALUE * Math.random() / 3)) + "";

        return res;
    }

}
