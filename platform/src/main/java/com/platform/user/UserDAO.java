package com.platform.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column(name="last_name")
    private String firstName;

    @Column(name="first_name")
    private String lastName;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
//    private List<JobDAO> listAuthorities = new ArrayList<>();

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
//
//    public List<JobDAO> getListAuthorities() {
//        return listAuthorities;
//    }
//
//    public void setListAuthorities(List<JobDAO> listAuthorities) {
//        this.listAuthorities = listAuthorities;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}