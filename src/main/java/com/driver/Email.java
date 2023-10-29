package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(this.password != oldPassword) return;

        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digit = false;
        boolean specialChar = false;

        for(char ch: newPassword.toCharArray()) {
            if(ch - 'a' <= 25 && ch - 'a' >= 0)
                lowerCase = true;
            else if(ch - 'A' <= 25 && ch - 'A' >= 0)
                upperCase = true;
            else if(ch - '0' <= 9 && ch - '0' >= 0)
                digit = true;
            else specialChar = true;
        }

        if(newPassword.length() >= 8 && upperCase && lowerCase && digit && specialChar)
            this.password = newPassword;
    }
}
