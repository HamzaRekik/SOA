package com.springboot.reglement.Controllers;
import com.springboot.reglement.Entities.User;
import com.springboot.reglement.Repositories.UserRepository;
import com.springboot.reglement.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(authService.loginUser(user.getEmail(), user.getPassword()));
        } catch (RuntimeException e) {
            String errorMessage;
            if (e.getMessage().equals("User not found")) {
                errorMessage = "User not found";
            } else if (e.getMessage().equals("Invalid  password")) {
                errorMessage = "Invalid password";
            } else {
                errorMessage = "Authentication failed";
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }



}
