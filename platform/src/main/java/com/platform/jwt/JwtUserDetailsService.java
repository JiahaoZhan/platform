package com.platform.jwt;

import java.util.ArrayList;

import com.platform.user.UserDAO;
import com.platform.user.UserDTO;
import com.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;

    private PasswordEncoder bcryptEncoder;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepo, @Lazy PasswordEncoder bcryptEncoder) {
        this.userRepo = userRepo;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public UserDAO save(UserDTO user) {
            // check if the user exists
            UserDAO dbUser = userRepo.findByEmail(user.getEmail());
            if (dbUser == null) {
                UserDAO newUser = new UserDAO();
                newUser.setEmail(user.getEmail());
                newUser.setFirstName(user.getFirst_name());
                newUser.setLastName(user.getLast_name());
                newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
                return userRepo.save(newUser);
            }
            return null;
    }
}