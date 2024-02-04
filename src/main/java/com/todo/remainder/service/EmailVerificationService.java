package com.todo.remainder.service;

import com.todo.remainder.entity.User;
import com.todo.remainder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailVerificationService {

    private JavaMailSender javaMailSender;

    private UserRepository userRepository;

    @Autowired
    public EmailVerificationService(JavaMailSender javaMailSender, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    public void sendVerificationEmail(User user){
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
        user.setVerified(false);

        userRepository.save(user);

        String verificationUrl = "http://localhost:8080/verify?token=" + token;
        String emailBody = "Please click the following link to verify your email: " + verificationUrl;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Email verification");
        mailMessage.setText(emailBody);

        javaMailSender.send(mailMessage);
    }

    public boolean verify(String token){
        User user = userRepository.findByVerificationToken(token);

        if(user != null){
            user.setVerified(true);
            user.setVerificationToken(null);
            userRepository.save(user);
            return true;
        }

        return false;
    }
}

