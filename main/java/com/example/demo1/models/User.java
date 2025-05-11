package com.example.demo1.models;

import java.time.LocalDate;

public class User {

    private String id;
    private String lastName;
    private String firstName;
    private String gender;
    private LocalDate birthDate;
    private String email;
    private String address;
    private String username;
    private String password;

    // Constructor
    public User(String id, String lastName, String firstName, String gender, String birthDate,
                String email, String address, String username, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = LocalDate.parse(birthDate);
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to validate the user (simple hardcoded validation for example purposes)
    public boolean isValid() {
        // In a real-world application, you would check against a database or other secure source
        return "admin".equals(username) && "password".equals(password); // Example valid credentials
    }

}
