package c24_10_2017_Reflecting;
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        int b = in.nextInt();
        int n = in.nextInt();
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            x = in.nextInt();
            y = in.nextInt();
            System.err.println("" + x + " " + y + " axis: " + n);
        }
        

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("" + x + " " + (-y));
    }
}