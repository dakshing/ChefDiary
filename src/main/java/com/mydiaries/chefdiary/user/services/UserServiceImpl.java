package com.mydiaries.chefdiary.user.services;

import com.mydiaries.chefdiary.login.Login;
import com.mydiaries.chefdiary.user.User;
import com.mydiaries.chefdiary.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isEmailUnique(String email) {
        return userRepository.findByMailIdEquals(email) == null;
    }

    @Override
    public User authorizeAndGetUser(Login login) {
        User user = userRepository.findByUserNameEquals(login.getUserName());
        return user.getPassword().equals(login.getPassword()) ? user : null;
    }
}
