package Utilities;

import java.util.Scanner;

// Utilities for input stuff
public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static String wString() {
        return scanner.nextLine();
    }

    public static int wIntRange(int min, int max, String sign) {
        while (true) {
            int dim = Input.wInt();
            if (dim >= min && dim <= max) {
                return dim;
            } else {
                System.err.println("Invalid " + sign +". Please choose again.");
            }
        }
    }

    // return an input of int
    public static int wInt() {
        while (true) {
            String str = scanner.nextLine();
            Integer i = convertInt(str);
            if (i != null) {
                return i;
            }
        }
    }

    // try converting a String into an Integer
    public static Integer convertInt(String str) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            System.err.println("Invalid input. Please input again.");
        }
        return null;
    }
}
