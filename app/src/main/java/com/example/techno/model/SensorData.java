package com.example.techno.model;

import java.time.LocalDateTime;
import java.util.Date;


public class SensorData {

    private int row_no;
    private int humidity;
    private int temp;
    private Date date ;

    public int getRow_no() {
        return row_no;
    }

    public void setRow_no(int row_no) {
        this.row_no = row_no;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        humidity = humidity;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "row_no=" + row_no +
                ", Humidity=" + humidity +
                ", temp=" + temp +
                ", date=" + date +
                '}';
    }
}
