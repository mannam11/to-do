package com.todo.remainder.service;

import com.todo.remainder.entity.User;
import com.todo.remainder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public User findUser(String email){


        return userRepository.findByEmail(email);
    }


}
