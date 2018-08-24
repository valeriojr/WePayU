package Util.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static Integer getInt(String prompt){
        try {
            System.out.println(prompt);
            int result = scanner.nextInt();
            scanner.nextLine();
            return result;
        } catch (InputMismatchException e) {
            System.out.println("Número inválido.");
        }
        return null;
    }

    public static String getString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
