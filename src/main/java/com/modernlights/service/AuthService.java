package com.modernlights.service;

import com.modernlights.exception.SellerException;
import com.modernlights.exception.UserException;
import com.modernlights.request.LoginRequest;
import com.modernlights.request.SignupRequest;
import com.modernlights.response.AuthResponse;
import jakarta.mail.MessagingException;

public interface AuthService {

    void sentLoginOtp(String email) throws UserException, MessagingException;
    String createUser(SignupRequest req) throws SellerException;
    AuthResponse signin(LoginRequest req) throws SellerException;

}
