package com.example.tbkproject.service;

import com.example.tbkproject.data.documents.UserDocument;
import com.example.tbkproject.data.repositories.UserRepository;
import com.example.tbkproject.exceptions.exception.user.InvalidCredentialsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GeneralService generalService;

    public void createUser(UserDocument user) {
        encodeUserPassword(user);
        userRepository.save(user);
    }

    private void encodeUserPassword(UserDocument userDocument) {
        String unHashedPassword = userDocument.getPassword();
        String hashedPassword = passwordEncoder.encode(unHashedPassword);
        userDocument.setPassword(hashedPassword);
    }

    public void loginUser(HttpServletRequest request, String email, String rawPassword) {
        UserDocument user = userRepository.findByEmail(email).orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        generalService.endSession(request);
        generalService.startAdminSession(request);
    }

    public void logoutUser(HttpServletRequest request) {
        generalService.endAdminSession(request);
    }

}
