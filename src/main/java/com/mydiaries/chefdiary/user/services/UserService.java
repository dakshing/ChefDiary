package com.mydiaries.chefdiary.user.services;

import com.mydiaries.chefdiary.login.Login;
import com.mydiaries.chefdiary.user.User;

public interface UserService {
    boolean isEmailUnique(String email);

    User authorizeAndGetUser(Login login);
}
