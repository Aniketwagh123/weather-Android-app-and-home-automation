package com.example.techno.model;



public class Phone {
    private int rowsNo;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Phone{" +
                "rowsNo=" + rowsNo +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public void setRowsNo(int rowsNo) {
        this.rowsNo = rowsNo;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRowsNo() {
        return rowsNo;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }
}
