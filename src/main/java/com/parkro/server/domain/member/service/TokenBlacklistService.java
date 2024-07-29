package com.parkro.server.domain.member.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Log4j2
@Service
public class TokenBlacklistService {

    private final Set<String> blacklist = new HashSet<>();

    public void addToken(String token) {

        blacklist.add(token);

    }

    public boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }
}