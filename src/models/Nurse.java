/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author dell
 */
public class Nurse extends Person implements Serializable {

    private String staffID;
    private String department;
    private String shift;
    private double salary;
    private int patientAssigned;

    public Nurse() {
    }

    public Nurse(String name, int age, String gender, String address, String phone,
            String staffID, String department, String shift, double salary) {
        super(staffID, name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
        this.patientAssigned = 0;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getPatientAssigned() {
        return patientAssigned;
    }

    public void setPatientAssigned(int patientAssigned) {
        this.patientAssigned = patientAssigned;
    }

}
