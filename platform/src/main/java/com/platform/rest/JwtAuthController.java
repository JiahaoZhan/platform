package com.platform.rest;

import com.platform.user.UserDAO;
import com.platform.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.platform.jwt.JwtTokenUtil;
import com.platform.jwt.JwtUserDetailsService;
import com.platform.jwt.JwtResponse;
import com.platform.jwt.JwtRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class JwtAuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsService userDetailsService;

    @Autowired
    public JwtAuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        UserDAO dbUser = userDetailsService.save(user);
        if (dbUser == null) return ResponseEntity.ok("The email has been taken");
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        System.out.println(authenticationRequest.getEmail());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}