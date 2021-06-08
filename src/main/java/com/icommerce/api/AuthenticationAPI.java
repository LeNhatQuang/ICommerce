package com.icommerce.api;

import com.icommerce.dto.JwtResponseDTO;
import com.icommerce.dto.LoginRequestDTO;
import com.icommerce.dto.MessageResponseDTO;
import com.icommerce.dto.SignupRequestDTO;
import com.icommerce.entity.RoleEntity;
import com.icommerce.entity.UserEntity;
import com.icommerce.enumtype.RoleEnum;
import com.icommerce.repository.RoleRepository;
import com.icommerce.repository.UserRepository;
import com.icommerce.service.UserDetailsImpl;
import com.icommerce.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationAPI {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("signin")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@Validated @RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication((authentication));
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseDTO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("signup")
    public ResponseEntity<MessageResponseDTO> registerUser(@Validated @RequestBody SignupRequestDTO signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponseDTO("Error: Username is already taken!"));
        }

        Set<String> strRoles = signUpRequest.getRoles();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
            RoleEntity role = roleRepository.findByRole(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role not found!"));
            roles.add(role);
        } else {
            for(String strRole : strRoles) {
                switch (strRole) {
                    case "admin":
                        return ResponseEntity.badRequest().body(
                                new MessageResponseDTO("Error: Cannot create admin user"));

                    case "mod":
                        SimpleGrantedAuthority role = new SimpleGrantedAuthority(RoleEnum.ADMIN.name());
                        if(!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(role)) {
                            return ResponseEntity.badRequest().body(
                                    new MessageResponseDTO("Error: you cannot create moderator user"));
                        }

                        RoleEntity modRole = roleRepository.findByRole(RoleEnum.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found!"));
                        roles.add(modRole);
                        break;

                    default:
                        RoleEntity userRole = roleRepository.findByRole(RoleEnum.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found!"));
                        roles.add(userRole);
                }
            }
        }

        UserEntity user = new UserEntity(signUpRequest.getUsername(), encoder.encode(signUpRequest. getPassword()),
                signUpRequest.getFullName(), signUpRequest.getEmail(), roles, null);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDTO("User registered successfully!"));
    }
}
