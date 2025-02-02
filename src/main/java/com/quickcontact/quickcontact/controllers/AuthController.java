package com.quickcontact.quickcontact.controllers;

import com.quickcontact.quickcontact.dto.AuthRequestDTO;
import com.quickcontact.quickcontact.dto.AuthResponseDTO;
import com.quickcontact.quickcontact.services.AuthService;
import com.quickcontact.quickcontact.services.BlacklistedTokenService;
import com.quickcontact.quickcontact.services.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/rest/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final BlacklistedTokenService blacklistedTokenService;
    private final JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthRequestDTO request) {
        try {
            AuthResponseDTO response = authService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = extractToken(request);
        if (token != null) {
            Instant expiration = jwtService.getExpirationDateFromToken(token);
            blacklistedTokenService.blacklistToken(token, expiration);
            SecurityContextHolder.clearContext();
        }
        return ResponseEntity.ok("Logged out successfully");
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
