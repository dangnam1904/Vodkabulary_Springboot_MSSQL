package com.project.models;

public class ChangePwdDTO {
    public String userID;

    public String password;
    public String newPassword;
    public String confirmPassword;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
//        checkPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
//        checkPassword();
    }
//    private void checkPassword() {
//        if(this.newPassword == null || this.confirmPassword == null){
//            return;
//        }else if(!this.newPassword.equals(confirmPassword)){
//            this.confirmPassword = null;
//        }
//    }

}
