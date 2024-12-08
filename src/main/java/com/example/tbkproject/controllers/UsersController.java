package com.example.tbkproject.controllers;

import com.example.tbkproject.dto.UserDto;
import com.example.tbkproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(HttpServletRequest request, @RequestBody UserDto userDto) {
        userService.loginUser(request, userDto.getEmail(), userDto.getPassword());

        return ResponseEntity.ok("User logged in and session started");
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(HttpServletRequest request) {
        userService.logoutUser(request);
        return ResponseEntity.noContent().build();
    }

}
