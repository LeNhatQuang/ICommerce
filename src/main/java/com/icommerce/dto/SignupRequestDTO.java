package com.icommerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class SignupRequestDTO {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private Set<String> roles;
}
