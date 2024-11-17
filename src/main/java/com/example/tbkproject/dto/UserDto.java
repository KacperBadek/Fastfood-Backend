package com.example.tbkproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String email;
    private String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
