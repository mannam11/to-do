package com.todo.remainder.service;

import com.todo.remainder.entity.User;
import com.todo.remainder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final int length=10;

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public User findUser(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findUser(username);

        if(user.isVerified() == false && user.getVerificationToken() != null){
            return null;
        }

        if(user == null){
            return null;
        }

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public String generatePassword(){
        String pattern = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$&*()";

        Random random=new Random();
        StringBuilder password = new StringBuilder();

        for(int i=1; i<=length; i++){
            int index = random.nextInt(pattern.length());
            password.append(pattern.charAt(index));
        }

        return password.toString();
    }
}
