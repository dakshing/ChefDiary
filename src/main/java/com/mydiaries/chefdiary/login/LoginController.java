package com.mydiaries.chefdiary.login;

import com.mydiaries.chefdiary.common.APIError;
import com.mydiaries.chefdiary.user.User;
import com.mydiaries.chefdiary.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> loginActivity(@Valid @RequestBody Login login) {
        User user = userService.authorizeAndGetUser(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        APIError apiError = new APIError(HttpStatus.UNAUTHORIZED, "User name or password incorrect.", "User not authorized.");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
