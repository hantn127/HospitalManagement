/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dell
 */
public class Utilities {

    private static Scanner sc = new Scanner(System.in);

    public static String phoneNumberChecker(String inputMessage) {
    Scanner sc = new Scanner(System.in);
    Pattern pattern = Pattern.compile("^\\d{10}$");
    
    while (true) {
        try {
            System.out.print(inputMessage);
            String phoneNumber = sc.nextLine().trim();
            Matcher matcher = pattern.matcher(phoneNumber);
            
            if (!matcher.matches()) {
                throw new Exception("Phone number must contain exactly 10 digits.");
            }
            
            return phoneNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

    public static String departmentFieldChecker(String inputMessage) {
        while (true) {
            try {
                System.out.print(inputMessage);
                String departmentField = sc.nextLine().trim();
                if (departmentField.length() < 3 || departmentField.length() > 50) {
                    throw new Exception("The length of the department field must be from 3 to 50 characters.");
                }
                return departmentField;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static int ageChecker(String inputMessage) {
        while (true) {
            try {
                System.out.print(inputMessage);
                String input = sc.nextLine().trim();
                if (input.trim().isEmpty()) {
                    throw new Exception("The age field must be a positive number.");
                }
                int age = Integer.parseInt(input);
                if (age <= 0) {
                    throw new Exception("The age field must be a positive number.");
                }
                return age;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static double salaryChecker(String inputMessage) {
        while (true) {
            try {
                System.out.print(inputMessage);
                String input = sc.nextLine().trim();
                if (input.trim().isEmpty()) {
                    throw new Exception("The salary field must be a positive number.");
                }
                double salary = Double.parseDouble(input);
                if (salary <= 0) {
                    throw new Exception("The salary field must be a positive number.");
                }
                return salary;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static String checkFormatString(String inputMessage, String errorMessage, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMessage);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMessage);
            } else {
                return id;
            }
        }
    }

    public static String getString(String inputMessage, String errorMessage) {
        String id;
        while (true) {
            System.out.print(inputMessage);
            id = sc.nextLine().trim();
            if (id.length() == 0 || id.isEmpty()) {
                System.out.println(errorMessage);
            } else {
                return id;
            }
        }
    }

    public static String repeat(String s, int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += s;
        }
        return result;
    }

    public static boolean deleteChecker() {
        System.out.print("Are you sure you want to delete the nurse?(Y/N): ");
        String userInput = sc.nextLine().trim();
        if (userInput.equals("Y")) {
            return true;
        } else if (userInput.equals("N")) {
            return false;
        } else {
            System.out.println("Please enter (Y/N).");
            return deleteChecker();
        }
    }
    
    public static Date getDate(String inputMessage, String errorMessage) {
    String inputDate;
    while (true) {
        System.out.print(inputMessage);
        inputDate = sc.nextLine().trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(inputDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            if (year < 1000 || year > 9999) {
                System.out.println(errorMessage);
                continue;
            }
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = outputFormat.format(date);
            return date;
        } catch (ParseException ex) {
            System.out.println(errorMessage);
        }
    }
}


}
