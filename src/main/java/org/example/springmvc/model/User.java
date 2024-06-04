package org.example.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;

    private String age;

    private String address;

    private String password;

    private String phone;

    private String code;


    private String token;
}
