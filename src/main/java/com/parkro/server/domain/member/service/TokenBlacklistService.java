package com.parkro.server.domain.member.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Log4j2
@Service
public class TokenBlacklistService {

    private final Set<String> blacklist = new HashSet<>();

    /**
     * 블랙리스트 내 토큰 등록
     * @param token
     */
    public void addToken(String token) {

        blacklist.add(token);

    }

    /**
     * 블랙리스트 내 토큰 여부 확인
     * @param token
     * @return Boolean 블랙리스트 내 토큰 등록 여부
     */
    public boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }
}