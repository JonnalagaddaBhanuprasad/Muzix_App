package com.bej.authentication.controller;

import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.security.SecurityTokenGenerator;
import com.bej.authentication.service.IUserService;
import com.bej.authentication.domain.User;
import com.bej.authentication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v2")
public class UserController {
    // Autowire the IUserService, SecurityTokenGenerator using constructor autowiring
    private  UserServiceImpl userService;
    private  SecurityTokenGenerator securityTokenGenerator;
    private ResponseEntity<?> responseEntity;
    @Autowired
    public UserController(UserServiceImpl userService,SecurityTokenGenerator securityTokenGenerator)
    {
        this.securityTokenGenerator = securityTokenGenerator;
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        // Write the logic to save a user,
        // return 201 status if user is saved else 500 status
     try {
         User user1 = userService.saveUser(user);
         responseEntity = new ResponseEntity<>(user1,HttpStatus.CREATED);
     }
     catch(UserAlreadyExistsException e)
     {
         throw new UserAlreadyExistsException();
     }

        return responseEntity;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException
    {
        // Generate the token on login,
        // return 200 status if user is saved else 500 status
        try
        {
            User loggedInUser = userService.getUserByUserIdAndPassword(user.getEmail(), user.getPassword());

                String userToken = securityTokenGenerator.createToken(loggedInUser);
                responseEntity = new ResponseEntity<>(userToken,HttpStatus.OK);
            System.out.println(responseEntity);
        }
        catch (InvalidCredentialsException e)
        {
            throw new InvalidCredentialsException();
        }
        return responseEntity;
    }
}
