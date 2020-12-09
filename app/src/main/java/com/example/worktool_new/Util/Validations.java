package com.example.worktool_new.Util;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class Validations {
    public static String error_message = "";

    public boolean Isemailidvalid(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public boolean validateRegistartionDataFirstStep(String firstName, String lastName, String dateOfBirth, String address, String city, String state, String zipCode, String emailAddress, String licenseId, String mobileNumber) {
        if (firstName.length() < 2) {
            error_message = " First Name must be at least 2 characters long.";
            return false;
        } else if (firstName.length() > 35) {
            error_message = "First Name must not exceed 35 characters.";
            return false;
        } else if (lastName.length() < 2) {
            error_message = " Last Name must be at least 2 characters long.";
            return false;
        } else if (lastName.length() > 35) {
            error_message = "Last Name must not exceed 35 characters.";
            return false;
        } else if (TextUtils.isEmpty(dateOfBirth)) {
            error_message = "Please enter date of Birth";
            return false;
        } else if (TextUtils.isEmpty(address)) {
            error_message = "Please enter the address.";
            return false;
        } else if (TextUtils.isEmpty(city) && city != null) {
            error_message = "Please enter city";
            return false;
        } else if (state.equals("Please select state")) {
            error_message = "Please enter state";
            return false;
        } else if (TextUtils.isEmpty(zipCode) && zipCode != null) {
            error_message = "Please enter zipCode";
            return false;
        } else if (TextUtils.isEmpty(emailAddress) && emailAddress != null) {
            error_message = "Please enter email Address";
            return false;
        } else if (!Isemailidvalid(emailAddress)) {
            error_message = "Please enter email valid Address";
            return false;
        } else if (TextUtils.isEmpty(licenseId) && licenseId != null) {
            error_message = "Please enter licenseId";
            return false;
        } else if (TextUtils.isEmpty(mobileNumber) && mobileNumber != null) {
            error_message = "Please enter mobile Number";
            return false;
        } else if (mobileNumber.length() >= 5) {
            return true;
        } else {
            error_message = "Please enter a valid mobile number";
            return false;
        }
    }

    public boolean validateAddEventdata(String type, String date, String time, String desc, String summary) {
        if (TextUtils.isEmpty(type)) {
            error_message = "Please enter a type.";
            return false;
        } else if (TextUtils.isEmpty(date)) {
            error_message = "Please select a date.";
            return false;
        } else if (TextUtils.isEmpty(time)) {
            error_message = "Please select a time.";
            return false;
        } else if (TextUtils.isEmpty(desc)) {
            error_message = "Please select a description.";
            return false;
        } else if (!TextUtils.isEmpty(summary)) {
            return true;
        } else {
            error_message = "Please select a summary.";
            return false;
        }
    }

    public boolean validateSignatureData(String uploadphoto) {
        if (!TextUtils.isEmpty(uploadphoto)) {
            return true;
        }
        error_message = "Please upload a  photo";
        return false;
    }

    public boolean ValidateWallPostData(String file, String message) {
        if (!TextUtils.isEmpty(file) || !TextUtils.isEmpty(message)) {
            return true;
        }
        error_message = "Please select a file or enter a message.";
        return false;
    }

    public boolean ValidateLoginData(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            error_message = "Please enter a email.";
            return false;
        } else if (!Isemailidvalid(email)) {
            error_message = "Please enter a valid email.";
            return false;
        } else if (!TextUtils.isEmpty(password)) {
            return true;
        } else {
            error_message = "Please enter a password.";
            return false;
        }
    }

    public boolean ValidateComposeMailPostData(String Subject, String message) {
        if (TextUtils.isEmpty(Subject)) {
            error_message = "Please enter a Subject.";
            return false;
        } else if (!TextUtils.isEmpty(message)) {
            return true;
        } else {
            error_message = "Please enter a message.";
            return false;
        }
    }

    public boolean ValidateWallPostDataPro(String file, String message) {
        if (!TextUtils.isEmpty(file) && !TextUtils.isEmpty(message)) {
            return true;
        }
        error_message = "Please select a file or enter a message";
        return false;
    }
}
