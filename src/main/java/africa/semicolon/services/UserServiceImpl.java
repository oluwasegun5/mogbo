package africa.semicolon.services;

import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dto.requests.LoginUserRequest;
import africa.semicolon.dto.requests.RegisterUserRequest;
import africa.semicolon.dto.responses.LoginUserResponse;
import africa.semicolon.dto.responses.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserServices{
    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(savedUser.getEmail());
        response.setDateCreated(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(savedUser.getDateRegistered()));

        return response;
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest){
        var theUser = userRepository.findByEmail(loginUserRequest.getEmail()).orElseThrow(()->
                new RuntimeException("User not found!"));
        LoginUserResponse response = new LoginUserResponse();

        if(theUser.getPassword().equals(loginUserRequest.getPassword())){
            response.setEmail(loginUserRequest.getEmail());
            response.setStatusMessage("Login successful!");
        }
        return response;

    }
}
