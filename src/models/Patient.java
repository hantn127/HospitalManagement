/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author dell
 */
public class Patient extends Person implements Serializable {

    private String diagnosis;
    private Date admissionDate;
    private Date dischargeDate;
    private HashMap<String, Nurse> nurseAssigned;

    public Patient() {
    }

    public Patient(String id, String name, int age, String gender, String address, String phone, String diagnosis, Date admissionDate, Date dischargeDate, HashMap<String, Nurse> nurseAssigned) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public HashMap<String, Nurse> getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(HashMap<String, Nurse> nursesAssigned) {
        this.nurseAssigned = nurseAssigned;
    }

    public String showInfor(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(getAdmissionDate());
        return String.format("#%10s#%15s#%15s#%15s#%15s#%15s#\n", count, this.getId(), formattedDate, this.getName(), this.getPhone(), this.getDiagnosis());
    }

}
