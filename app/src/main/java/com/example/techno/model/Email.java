package com.example.techno.model;



public class Email {
    private int rowsNo;
    private String emailId;

    @Override
    public String toString() {
        return "Email{" +
                "rowsNo=" + rowsNo +
                ", emailId='" + emailId + '\'' +
                '}';
    }

    public void setRowsNo(int rowsNo) {
        this.rowsNo = rowsNo;
    }


    public String getEmailId() {
        return emailId;
    }

    public int getRowsNo() {
        return rowsNo;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

}
