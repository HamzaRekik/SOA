package com.springboot.reglement.Services;

import com.springboot.reglement.Entities.User;
import com.springboot.reglement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }


        return user;
    }


}
