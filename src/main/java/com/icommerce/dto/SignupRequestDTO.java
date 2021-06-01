package com.icommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequestDTO {
    private String username;
    private String email;
    private String password;
    private String fullname;
    private Set<String> roles;
}
