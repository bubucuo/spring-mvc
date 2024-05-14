package org.example.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;

    private String password;

    private String token;
}
