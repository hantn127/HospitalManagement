/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import models.Nurse;
import models.Patient;

/**
 *
 * @author dell
 */
public class Hospital {

    private HashMap<String, Nurse> nurses;
    private HashMap<String, Patient> patients;

    public Hospital() {
        this.nurses = new HashMap<>();
        this.patients = new HashMap<>();
    }

    public void saveNursesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("nurses.dat"))) {
            oos.writeObject(nurses);
            System.out.println("Nurses data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving nurses data: " + e.getMessage());
        }
    }

    public void loadNursesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("nurses.dat"))) {
            nurses = (HashMap<String, Nurse>) ois.readObject();
            System.out.println("Nurses data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading nurses data: " + e.getMessage());
        }
    }

    public void savePatientsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("patients.dat"))) {
            oos.writeObject(patients);
            System.out.println("Patients data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving patients data: " + e.getMessage());
        }
    }

    public void loadPatientsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("patients.dat"))) {
            patients = (HashMap<String, Patient>) ois.readObject();
            System.out.println("Patients data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading patients data: " + e.getMessage());
        }
    }

    public void createNurse() {
        boolean repeat = false;
        String staffID = null, name, department, gender, address, phone, shift;
        int age;
        double salary;
        while (!repeat) {
            staffID = Utilities.checkFormatString("Enter staff id: ", "Staff Id must be formatted as Nxxxx with x is a digit", "^N\\d{4}$");
            if (nurses.get(staffID) != null) {
                System.out.println("The staffID field must be unique.");
                repeat = false;
            } else {
                repeat = true;
            }
        }
        name = Utilities.getString("Enter name: ", "Name can not be empty!");
        gender = Utilities.getString("Enter gender: ", "Gender can not be empty!");
        address = Utilities.getString("Enter address: ", "Address can not be empty!");
        shift = Utilities.getString("Enter shift (Day or Night): ", "Shift can not be empty!");
        department = Utilities.departmentFieldChecker("Enter department field: ");
        phone = Utilities.phoneNumberChecker("Enter phone number: ");
        age = Utilities.ageChecker("Enter age: ");
        salary = Utilities.salaryChecker("Enter salary: ");
        Nurse nurse = new Nurse(name, age, gender, address, phone, staffID, department, shift, salary);
        nurses.put(staffID, nurse);
        repeatCreateNurse();
    }

    private void repeatCreateNurse() {
        String userOption = Utilities.getString("Create a nurse successfully! Do you want to add more?[1/0-Y/N-T/F]: ", "You must fill in the form");
        if (userOption.equals("1") || userOption.equals("Y") || userOption.equals("T")) {
            createNurse();
        } else if (userOption.equals("0") || userOption.equals("N") || userOption.equals("F")) {
            return;
        } else {
            System.out.println("Please enter [1/0-Y/N-T/F]");
            repeatCreateNurse();
        }
    }

    private void displayNurseDetails(boolean found, String name) {
        if (found) {
            System.out.println(Utilities.repeat("#", 145));
            System.out.printf("#%15s#%15s#%15s#%15s#%15s#%15s#%15s#%15s#%15s#\n", "Staff ID", "Name", "Age", "Gender", "Address", "Shift", "Department", "Phone", "Salary");
            System.out.println(Utilities.repeat("#", 145));
            for (Nurse nurse : nurses.values()) {
                if (nurse.getName().toLowerCase().contains(name.toLowerCase())) {
                    System.out.printf("#%15s#%15s#%15s#%15s#%15s#%15s#%15s#%15s#%15s#\n", nurse.getStaffID(), nurse.getName(), nurse.getAge(),
                            nurse.getGender(), nurse.getAddress(), nurse.getShift(), nurse.getDepartment(), nurse.getPhone(), nurse.getSalary());
                }
            }
            System.out.println(Utilities.repeat("#", 145));
        } else {
            System.out.println("The nurse does not exist.");
        }
    }

    public void findNurses() {
        if (nurses.isEmpty()) {
            System.out.println("No nurse to search!");
            return;
        } else {
            String name = Utilities.getString("Enter name: ", "You must fill in the name");
            boolean found = false;
            for (Nurse nurse : nurses.values()) {
                if (nurse.getName().toLowerCase().contains(name.toLowerCase())) {
                    found = true;
                }
            }
            displayNurseDetails(found, name);
        }
    }

    public void updateNurse() {
        String staffID = Utilities.checkFormatString("Enter staff id: ", "Staff Id must be formatted as Nxxxx with x is a digit", "^N\\d{4}$");
        Nurse nurse = nurses.get(staffID);
        if (nurse != null) {
            System.out.println("Check staff ID successfully. Now you can update information of the nurse");
            String name = Utilities.getString("Enter name: ", "Name can not be empty!");
            String gender = Utilities.getString("Enter gender: ", "Gender can not be empty!");
            String address = Utilities.getString("Enter address: ", "Address can not be empty!");
            String shift = Utilities.getString("Enter shift (Day or Night): ", "Shift can not be empty!");
            String department = Utilities.departmentFieldChecker("Enter department field: ");
            String phone = Utilities.phoneNumberChecker("Enter phone number: ");
            int age = Utilities.ageChecker("Enter age: ");
            double salary = Utilities.salaryChecker("Enter salary: ");
            nurse.setName(name);
            nurse.setAge(age);
            nurse.setGender(gender);
            nurse.setAddress(address);
            nurse.setPhone(phone);
            nurse.setDepartment(department);
            nurse.setShift(shift);
            nurse.setSalary(salary);
            System.out.println("Update the nurse successfully!");
        } else {
            System.out.println("The nurse does not exist");
        }
    }

    public void deleteNurse() {
        String staffID = Utilities.checkFormatString("Enter staff id: ", "Staff Id must be formatted as Nxxxx with x is a digit", "^N\\d{4}$");
        Nurse nurse = nurses.get(staffID);
        if (nurse == null) {
            System.out.println("The nurse does not exist.");
        } else {
            if (nurseHasTask(staffID)) {
                System.out.println("The nurse cannot be deleted as she has a task (looking after a patient).");
            } else {
                if (Utilities.deleteChecker()) {
                    nurses.remove(staffID);
                    System.out.println("Nurse deleted successfully.");
                } else {
                    System.out.println("Deletion cancelled.");
                }
            }
        }
    }

    private boolean nurseHasTask(String staffID) {
        Nurse nurse = nurses.get(staffID);
        for (Patient patient : patients.values()) {
            if (patient.getNurseAssigned().containsKey(nurse.getStaffID())) {
                return true;
            }
        }
        return false;
    }

//    private boolean hasAvailableNurse() {
//        for (Nurse nurse : nurses.values()) {
//            if (nurse.getPatientAssigned() <= 1) {
//                return true;
//            }
//        }
//        return false;
//    }
    private boolean hasAvailableNurse() {
        int count = 0;
        Nurse firstNurse = null;
        for (Nurse nurse : nurses.values()) {
            if (nurse.getPatientAssigned() <= 1) {
                if (count == 0) {
                    firstNurse = nurse;
                } else if (!nurse.equals(firstNurse)) {
                    return true;
                }

                count++;
            }
        }
        return false;
    }

    public void addPatient() {
        if (!hasAvailableNurse()) {
            System.out.println("All nurses are currently busy and cannot take on more patients. Please add a new nurse first.");
            return;
        } else {
            String patientID = null;
            String name, gender, address, phone, diagnosis;
            int age;
            Date admissionDate, dischargeDate;

            boolean repeat = false;
            while (!repeat) {
                patientID = Utilities.checkFormatString("Enter patient id: ", "Patient Id must be formatted as Pxxxx with x is a digit", "^P\\d{4}$");
                if (patients.get(patientID) != null) {
                    System.out.println("The patient ID field must be unique.");
                    repeat = false;
                } else {
                    repeat = true;
                }
            }
            name = Utilities.getString("Enter name: ", "Name can not be empty!");
            age = Utilities.ageChecker("Enter age: ");
            gender = Utilities.getString("Enter gender: ", "Gender can not be empty!");
            address = Utilities.getString("Enter address: ", "Address can not be empty!");
            phone = Utilities.phoneNumberChecker("Enter phone number: ");
            diagnosis = Utilities.getString("Enter diagnosis: ", "Diagnosis can not be empty!");
            admissionDate = Utilities.getDate("Enter date of admission (dd/MM/yyyy): ", "Invalid date!");
            do {
                dischargeDate = Utilities.getDate("Enter date of discharge(dd/MM/yyyy): ", "Invalid date!");
            } while (dischargeDate.compareTo(admissionDate) < 0);
            HashMap<String, Nurse> nurseAssigned = addNurseToPatient();

            Patient patient = new Patient(patientID, name, age, gender, address, phone, diagnosis, admissionDate, dischargeDate, nurseAssigned);
            patient.setNurseAssigned(nurseAssigned);
            patients.put(patientID, patient);

            System.out.println("Add the patient successfully!");
            repeatAddPatient();
        }
    }

    private void displayNurse() {
        if (nurses.isEmpty()) {
            System.out.println("All nurses are currently busy and cannot take on more patients. Please add a new nurse first.");
            return;
        }
        System.out.println(Utilities.repeat("#", 166));
        System.out.printf("#%15s#%20s#%15s#%15s#%15s#%15s#%15s#%15s#%15s#%15s#\n", "Staff ID", "Patients Assigned", "Name", "Age", "Gender", "Address", "Shift", "Department", "Phone", "Salary");
        System.out.println(Utilities.repeat("#", 166));
        for (Nurse nurse : nurses.values()) {
            System.out.printf("#%15s#%20s#%15s#%15s#%15s#%15s#%15s#%15s#%15s#%15s#\n", nurse.getStaffID(), nurse.getPatientAssigned(), nurse.getName(), nurse.getAge(),
                    nurse.getGender(), nurse.getAddress(), nurse.getShift(), nurse.getDepartment(), nurse.getPhone(), nurse.getSalary());
        }
        System.out.println(Utilities.repeat("#", 166));
    }

    private HashMap<String, Nurse> addNurseToPatient() {
        HashMap<String, Nurse> nurseAssigned = new HashMap<>();
//        boolean isFirstNurseAssigned = false;
//        boolean isConditionMet = true;
        for (int i = 1; i <= 2; i++) {
            System.out.println("List of all nurses: ");
            displayNurse();
            String staffID = Utilities.checkFormatString("Enter nurse id " + i + ": ", "Nurse Id must be formatted as Nxxxx with x is a digit", "^N\\d{4}$");

            if (nurseAssigned.containsKey(staffID)) {
                System.out.println("This nurse has been assigned to this patient. Please choose another nurse.");
                i--;
                continue;
            }
            Nurse nurse = nurses.get(staffID);
            if (nurse == null) {
                System.out.println("Nurse does not exist. Please check the nurse list.");
                i--;
                continue;
            }
            if (nurse.getPatientAssigned() >= 2) {
                System.out.println("This nurse took care of the maximum number of patients. Please choose another nurse.");
                i--;
                continue;
            }
            nurse.setPatientAssigned(nurse.getPatientAssigned() + 1);
            nurseAssigned.put(staffID, nurse);
//
//            if (!isFirstNurseAssigned) {
//                boolean hasOtherNurseAvailable = false;
//                for (Nurse otherNurse : nurses.values()) {
//                    if (!otherNurse.getId().equals(staffID) && otherNurse.getPatientAssigned() < 2) {
//                        hasOtherNurseAvailable = true;
//                        break;
//                    }
//                }
//                if (!hasOtherNurseAvailable) {
//                    isConditionMet = false;
//                    break;
//                }
//                isFirstNurseAssigned = true;
//            }
        }
                
//        if (!isConditionMet) {
//            System.out.println("All nurses are currently busy and cannot take on more patients. Please add a new nurse first.");
//        }
        return nurseAssigned;
    }

    private void repeatAddPatient() {
        String userOption = Utilities.getString("Do you want to add more?[1/0-Y/N-T/F]: ", "You must fill in the form");
        if (userOption.equals("1") || userOption.equals("Y") || userOption.equals("T")) {
            addPatient();
        } else if (userOption.equals("0") || userOption.equals("N") || userOption.equals("F")) {
            return;
        } else {
            System.out.println("Please enter [1/0-Y/N-T/F]");
            repeatAddPatient();
        }
    }

    ArrayList<Patient> patientFound = new ArrayList<>();

    public void displayPatient() {
        patientFound.clear();
        if (patients.isEmpty()) {
            System.out.println("Nothing to display!");
            return;
        }
        Date startDate = Utilities.getDate("Enter start date (dd/MM/yyyy): ", "Invalid date!");
        Date endDate;
        do {
            endDate = Utilities.getDate("Enter end date (dd/MM/yyyy): ", "Invalid date!");
        } while (endDate.compareTo(startDate) <= 0);

        for (Patient patient : patients.values()) {
            Date admissionDate = patient.getAdmissionDate();
            if (admissionDate.compareTo(startDate) >= 0 && admissionDate.compareTo(endDate) <= 0) {
                patientFound.add(patient);
            }
        }
        displayPatientDetails(patientFound);
    }

    private void displayPatientDetails(ArrayList<Patient> listPatient) {
        if (listPatient.isEmpty()) {
            System.out.println("No patients during this period.");
            return;
        } else {
            System.out.println(Utilities.repeat("#", 92));
            System.out.printf("#%10s#%15s#%15s#%15s#%15s#%15s#\n", "No.", "Patient ID", "Admission Date", "Full Name", "Phone", "Diagnosis");
            System.out.println(Utilities.repeat("#", 92));
            int count = 1;
            for (Patient patient : listPatient) {
                System.out.print(patient.showInfor(count));
                count++;
            }
            System.out.println(Utilities.repeat("#", 92));
        }
    }

    public void sortPatient() {
        if (patients.isEmpty()) {
            System.out.println("Nothing to display!");
            return;
        }
        String sortField, sortOrder;
        boolean validInput;

        do {
            validInput = true;
            sortField = Utilities.getString("Sorted by (patient id or patient’s name): ", "you can't leave the input blank!").toLowerCase().trim();

            if (!sortField.equalsIgnoreCase("patient id") && !sortField.equalsIgnoreCase("patient's name")) {
                System.out.println("Invalid input. Please enter either 'Patient ID' or 'Patient's Name.");
                validInput = false;
            }
        } while (!validInput);

        do {
            validInput = true;
            sortOrder = Utilities.getString("Sort order: ", "you can't leave the input blank!").toLowerCase().trim();

            if (!sortOrder.equalsIgnoreCase("asc") && !sortOrder.equalsIgnoreCase("desc")) {
                System.out.println("Invalid input. Please enter either 'ASC' or 'DESC");
                validInput = false;
            }
        } while (!validInput);

        switch (sortField) {
            case "patient id":
                if (sortOrder.equalsIgnoreCase("asc")) {
                    sortPatientsById(true);
                } else if (sortOrder.equalsIgnoreCase("desc")) {
                    sortPatientsById(false);
                }
                break;
            case "patient's name":
                if (sortOrder.equalsIgnoreCase("asc")) {
                    sortPatientsByName(true);
                } else if (sortOrder.equalsIgnoreCase("desc")) {
                    sortPatientsByName(false);
                }
                break;
        }
    }

    private void sortPatientsById(boolean ascending) {
        patientFound.sort((Patient p1, Patient p2) -> p1.getId().compareTo(p2.getId()));
        if (!ascending) {
            Collections.reverse(patientFound);
        }
        displayPatientDetails(patientFound);
    }

    private void sortPatientsByName(boolean ascending) {
        patientFound.sort((Patient p1, Patient p2) -> p1.getName().compareTo(p2.getName()));
        if (!ascending) {
            Collections.reverse(patientFound);
        }
        displayPatientDetails(patientFound);
    }

}
