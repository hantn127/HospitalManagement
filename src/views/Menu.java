/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Hospital;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class Menu {

    public void operation() {

        Scanner sc = new Scanner(System.in);
        Hospital hospitalManagement = new Hospital();
        boolean loadConfirm = CommonMenu.loadChecker();
        if (!loadConfirm) {
            hospitalManagement.loadNursesFromFile();
            hospitalManagement.loadPatientsFromFile();
        }

        while (true) {
            System.out.println("----------Hospital Management Program----------");
            System.out.println("NURSE'S MANAGEMENT: ");
            System.out.println("\t1 - Create a nurse");
            System.out.println("\t2 - Find the nurse");
            System.out.println("\t3 - Update the nurse");
            System.out.println("\t4 - Delete the nurse");
            System.out.println("PATIENT'S MANAGEMENT: ");
            System.out.println("\t5 - Add a patient");
            System.out.println("\t6 - Lists patients");
            System.out.println("\t7 - Sort the patient list");
            System.out.println("LOAD AND SAVE MANAGEMENT: ");
            System.out.println("\t8 - Save data");
            System.out.println("\t9 - Load data");
            System.out.println("EXIT PROGRAM - Press others to quit");
            System.out.println("-----------------------------------------------");
            System.out.print("Enter your choice: ");
            String option = sc.nextLine().trim();

            switch (option) {
                case "1":
                    hospitalManagement.createNurse();
                    break;
                case "2":
                    hospitalManagement.findNurses();
                    break;
                case "3":
                    hospitalManagement.updateNurse();
                    break;
                case "4":
                    hospitalManagement.deleteNurse();
                    break;
                case "5":
                    hospitalManagement.addPatient();
                    break;
                case "6":
                    hospitalManagement.displayPatient();
                    break;
                case "7":
                    hospitalManagement.sortPatient();
                    break;
                case "8":
                    hospitalManagement.saveNursesToFile();
                    hospitalManagement.savePatientsToFile();
                    break;
                case "9":
                    hospitalManagement.loadPatientsFromFile();
                    hospitalManagement.loadNursesFromFile();
                    break;
                default:
                    boolean exitConfirm = CommonMenu.exitChecker();
                    if (!exitConfirm) {
                        break;
                    } else {
                        boolean saveConfirm = CommonMenu.saveChecker();
                        if (saveConfirm) {
                            hospitalManagement.saveNursesToFile();
                            hospitalManagement.savePatientsToFile();
                        }
                        System.out.println("Successfully exit the program.");
                    }
                    System.exit(0);
            }
        }
    }

}
