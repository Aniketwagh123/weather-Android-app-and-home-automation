package com.example.techno.model;


public class HomeAtomation {
    private int rowsNo;
    private String r1;
    private String r2;

    @Override
    public String toString() {
        return "HomeAtomation{" +
                "rowsNo=" + rowsNo +
                ", r1='" + r1 + '\'' +
                ", r2='" + r2 + '\'' +
                '}';
    }

    public int getRowsNo() {
        return rowsNo;
    }

    public void setRowsNo(int rowsNo) {
        this.rowsNo = rowsNo;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }




}
