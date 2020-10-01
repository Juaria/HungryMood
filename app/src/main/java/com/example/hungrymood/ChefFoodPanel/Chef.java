package com.example.hungrymood.ChefFoodPanel;

public class Chef {
    private String Address, ConfirmPassword, Emailid, Fname, Lname, Mobile, Password;

    // Press Alt+Insert


    public Chef(String address, String confirmPassword, String emailid, String fname, String lname, String mobile, String password) {
        this.Address = address;
        ConfirmPassword = confirmPassword;
        Emailid = emailid;
        Fname = fname;
        Lname = lname;
        Mobile = mobile;
        Password = password;
    }
    public Chef(){

    }

    public String getAddress() {
        return Address;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public String getEmailid() {
        return Emailid;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPassword() {
        return Password;
    }
}



