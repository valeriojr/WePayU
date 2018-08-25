package Util.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static Integer getInt(String message){
        try {
            System.out.println(message);
            int result = scanner.nextInt();
            scanner.nextLine();
            return result;
        } catch (InputMismatchException e) {
            System.out.println("Número inválido.");
        }
        return null;
    }

    public static Double getDouble(String message){
        try {
            System.out.println(message);
            double result = scanner.nextDouble();
            scanner.nextLine();
            return result;
        } catch (InputMismatchException e) {
            System.out.println("Número inválido.");
        }
        return null;
    }

    public static String getString(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    public static long getLong(String message) {
        System.out.println(message);
        return scanner.nextLong();
    }
}
