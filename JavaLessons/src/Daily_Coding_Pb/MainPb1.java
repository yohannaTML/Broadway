package Daily_Coding_Pb;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem n°1:
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 */

public class MainPb1 {

    public static void main(String[] args) {

        int[] tab = {10, 5, 3, 7};
        SumOfNumber(new ArrayList(Arrays.asList(tab)));
    }

    /*
    Autre methode pour transformer un tableau en arrayList:
    List al = new ArrayList();
    String[] titres = {"titre1, titre2,..."}
    Collections.addAll(al, titres);
     */
    private static void SumOfNumber(ArrayList<Integer> numbers) {
    }

}
