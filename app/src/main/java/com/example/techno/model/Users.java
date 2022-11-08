// This is an User model which will create new table User in database SpringServerDB with columns maintain in User class variables
package com.example.techno.model;


import java.util.Date;
import java.util.List;


public class Users {

    private  int userId;
    private String deviceId;
    private String userName;
    private String password;
    private String userLocation;
//    private Date purchaseDate;
    private boolean is_login;
    private Date date;

    private List<Email> emails;

    private List<Phone> phoneNumbers;

    private List<SensorData> sensor_data;

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", DeviceId='" + deviceId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", UserLocation='" + userLocation + '\'' +
//                ", purchaseDate=" + purchaseDate +
                ", is_login=" + is_login +
                ", emails=" + emails +
                ", phoneNumbers=" + phoneNumbers +
                ", sensor_data=" + sensor_data +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }


    public boolean isIs_login() {
        return is_login;
    }

    public void setIs_login(boolean is_login) {
        this.is_login = is_login;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<SensorData> getSensor_data() {
        return sensor_data;
    }

    public void setSensor_data(List<SensorData> sensor_data) {
        this.sensor_data = sensor_data;
    }
}
