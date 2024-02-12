package com.todo.remainder.service;

import com.todo.remainder.entity.User;
import com.todo.remainder.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(User user) {
        String hashPassword  = passwordEncoder.encode(user.getPassword().trim());
        user.setPassword(hashPassword);
        userRepository.save(user);
    }

    public User findUser(String email){
        return userRepository.findByEmail(email);
    }
}
