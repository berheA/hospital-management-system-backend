package com.berheamare.hospitalmanagementsystem.models;

public class JwtResponse {

    private User user;
    private String jwtToken;

    public JwtResponse(User user2, String jwtToken) {
        this.user = user2;
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}