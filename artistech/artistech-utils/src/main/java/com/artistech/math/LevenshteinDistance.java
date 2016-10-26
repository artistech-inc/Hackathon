/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.math;

import java.util.List;

/**
 * Calculate levenshtein/string-edit distance.
 *
 * @author matta
 */
public class LevenshteinDistance {

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static <T extends Comparable<T>> int computeLevenshteinDistance(List<T> str1, List<T> str2) {
        int[][] distance = new int[str1.size() + 1][str2.size() + 1];

        for (int i = 0; i <= str1.size(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= str2.size(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= str1.size(); i++) {
            for (int j = 1; j <= str2.size(); j++) {
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((str1.get(i - 1).compareTo(str2.get(j - 1)) == 0 ? 0 : 1)));
            }
        }

        return distance[str1.size()][str2.size()];
    }

    public static int computeLevenshteinDistance(String str1, String str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= str2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
            }
        }

        return distance[str1.length()][str2.length()];
    }
}
