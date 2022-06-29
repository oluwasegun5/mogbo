package africa.semicolon.controllers;

import africa.semicolon.dto.requests.LoginUserRequest;
import africa.semicolon.dto.requests.RegisterUserRequest;
import africa.semicolon.dto.responses.LoginUserResponse;
import africa.semicolon.dto.responses.RegisterUserResponse;
import africa.semicolon.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/user")
    public RegisterUserResponse register(@RequestBody RegisterUserRequest registerUserRequest){
        return userServices.registerUser(registerUserRequest);
    }

    @PostMapping("/login")
    public LoginUserResponse login(@RequestBody LoginUserRequest loginUserRequest){
        return userServices.loginUser(loginUserRequest);
    }
}
