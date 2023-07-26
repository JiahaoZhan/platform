package com.platform.jwt;

import java.io.Serializable;
public class JwtRequest implements Serializable {

    private String email;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}