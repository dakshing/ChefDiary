package com.mydiaries.chefdiary.login;

import javax.validation.constraints.NotNull;

public class Login {
    @NotNull(message = "User name can not be empty.")
    private String userName;
    @NotNull(message = "Password can not be empty.")
    private String password;

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
}
