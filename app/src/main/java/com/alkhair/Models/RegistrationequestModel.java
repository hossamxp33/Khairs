package com.alkhair.Models;

public class RegistrationequestModel {

    /**
     * Email : pava@yahoo.com
     * FirstName : pavan
     * LastName : Venkat
     * Mobile : 61789654
     * Active : 1
     * Password : test
     */

    private String Email;
    private String FirstName;
    private String LastName;
    private String Mobile;
    private String Active;
    private String Password;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
