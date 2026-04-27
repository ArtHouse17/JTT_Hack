package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.repository.UserRepository;
import com.jtt.javatachteam_hakaton.service.AuthService;
import io.javalin.http.Context;

public class AuthHandler {
    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthHandler(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    public void login(Context ctx) {
        LoginRequest req = ctx.bodyAsClass(LoginRequest.class);

        String token = authService.login(req.username(), req.password());
        ctx.status(200).json(new AuthResponse(token));
    }

    public void signup(Context ctx) {
        SignupRequest req = ctx.bodyAsClass(SignupRequest.class);
        String token = authService.signup(req.username(), req.password(), req.firstname(), req.lastname());
        ctx.status(201).json(new AuthResponse(token));
    }

    public void logout(Context ctx) {
        ctx.json(new ErrorResponse("Успешный выход"));
    }

    public record LoginRequest(String username, String password) {}
    public record SignupRequest(String username, String password, String firstname, String lastname) {}
    public record AuthResponse(String token) {}
    public record ErrorResponse(String message) {}
}
