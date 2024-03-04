package com.pet.mailSender.controllers;

import com.pet.mailSender.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final Validator validator;
    
    @GetMapping
    public OAuth2AuthenticationToken currentUser(OAuth2AuthenticationToken token) throws MessagingException {
        validator.validateCredentials(token);
        return token;
    }
}
