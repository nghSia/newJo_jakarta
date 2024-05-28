package com.jpo.newjpo.service;

import com.jpo.newjpo.DTO.LoginDTO;
import com.jpo.newjpo.DTO.RegisterDTO;
import com.jpo.newjpo.enums.UserRole;
import com.jpo.newjpo.modele.User;
import com.jpo.newjpo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterDTO input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setRole(UserRole.CUSTOMER);
        user.setArgent(0F);
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    @PostConstruct
    public void createAdminAccount(){
        User v_adminUser = userRepository.findByRole(UserRole.ADMIN);
        if(v_adminUser == null){
            User v_newUserAdmin = new User();
            v_newUserAdmin.setEmail("admin@admin.com");
            v_newUserAdmin.setRole(UserRole.ADMIN);
            v_newUserAdmin.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(v_newUserAdmin);
        }
    }

    public Boolean hasUserWithEmail(String p_email){
        return userRepository.findFirstByEmail(p_email).isPresent();
    }

    public User authenticate(LoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
