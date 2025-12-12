package com.crs.main.controller;

import com.crs.main.dto.UserDTO;
import com.crs.main.security.JwtService;
import com.crs.main.service.AuthenticationService;
import com.crs.main.security.LoginRequest;
import com.crs.main.security.LoginResponse;
import com.crs.main.security.UserRegisterRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                                    JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword());
        authenticationManager.authenticate(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", "")).toList();

        String token = jwtService.generateToken(userDetails.getUsername(), roles);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserRegisterRequest request) {
        UserDTO userDTO = authenticationService.registerUser(request);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().body("No authenticated user to log out.");
        }
        String username = authentication.getName();
        authenticationService.logout(username);
        return ResponseEntity.ok("Logged out successfully.");
    }
}