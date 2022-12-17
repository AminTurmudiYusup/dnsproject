package com.example.dnsproject.controller;

import com.example.dnsproject.dto.LoginRequest;
import com.example.dnsproject.dto.LoginResponse;
import com.example.dnsproject.exception.NotFoundException;
import com.example.dnsproject.service.JwtTokenService;
import com.example.dnsproject.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired//inject needed dependency using @Autowired or constructor
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        } catch (final BadCredentialsException badCredentialsException) {
            throw new NotFoundException("User with userName "+loginRequest.getUserName()+" NotFound");
        }
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUserName());
        final LoginResponse loginResponse=new LoginResponse();
        loginResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        loginResponse.setResponseCode(HttpStatus.OK.toString());
        loginResponse.setResponseStatus("SUCCESSFUL");
        return loginResponse;
    }
}
