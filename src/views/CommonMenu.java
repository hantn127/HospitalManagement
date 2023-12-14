/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.Scanner;

/**
 *
 * @author dell
 */
public class CommonMenu {

    private static Scanner sc = new Scanner(System.in);

    public static boolean loadChecker() {
        System.out.println("Press [1/Y/T] to create new data");
        System.out.println("Press [0/N/F] to load old data");
        System.out.print("Do you want to create new data or load old data: ");
        String userInput = sc.nextLine().trim();

        if (userInput.equals("1") || userInput.equals("Y") || userInput.equals("T")) {
            return true;
        } else if (userInput.equals("0") || userInput.equals("N") || userInput.equals("F")) {
            return false;
        } else {
            System.out.println("Please enter [1/0-Y/N-T/F]");
            return loadChecker();
        }
    }

    public static boolean exitChecker() {
        System.out.print("Do you want to exit program? [1/0-Y/N-T/F]: ");
        String userInput = sc.nextLine().trim();

        if (userInput.equals("1") || userInput.equals("Y") || userInput.equals("T")) {
            return true;
        } else if (userInput.equals("0") || userInput.equals("N") || userInput.equals("F")) {
            return false;
        } else {
            System.out.println("Please enter [1/0-Y/N-T/F]");
            return exitChecker();
        }
    }

    public static boolean saveChecker() {
        System.out.print("Do you want to save the data? [1/0-Y/N-T/F]: ");
        String userInput = sc.nextLine().trim();
        if (userInput.equals("1") || userInput.equals("Y") || userInput.equals("T")) {
            return true;
        } else if (userInput.equals("0") || userInput.equals("N") || userInput.equals("F")) {
            return false;
        } else {
            System.out.println("Please enter [1/0-Y/N-T/F]");
            return saveChecker();
        }
    }
}
