package com.quickcontact.quickcontact.services;


import com.quickcontact.quickcontact.entities.BlacklistedToken;
import com.quickcontact.quickcontact.repositories.BlacklistedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BlacklistedTokenService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;

    public void blacklistToken(String token, Instant expirationDate) {
        BlacklistedToken blacklistedToken = new BlacklistedToken(token, expirationDate);
        blacklistedTokenRepository.save(blacklistedToken);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokenRepository.findByToken(token).isPresent();
    }
}