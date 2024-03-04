package com.pet.mailSender.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountRequest {
    
    @NotEmpty(message = "{account.firstName.notEmpty}")
    @Pattern(regexp = "[A-Z][a-z]+", message = "{account.firstName.notValid}")
    private String firstName;
    
    @NotEmpty(message = "{account.lastName.notEmpty}")
    @Pattern(regexp = "[A-Z][a-z]+", message = "{account.lastName.notValid}")
    private String lastName;
    
    @Pattern(regexp = "[a-zA-Z0-9\\._-]+@[a-zA-Z0-9\\._-]+\\.[a-zA-Z0-9\\._-]+", message = "{account.email.noAt}")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "{account.password.notEmpty}")
    private String password;
    
}
