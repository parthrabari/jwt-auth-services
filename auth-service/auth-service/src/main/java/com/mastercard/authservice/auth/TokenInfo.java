package com.mastercard.authservice.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TokenInfo {

    @Value("${auth.jwt.url:/auth/**}")
    private String url;

    @Value("${auth.jwt.header:Authorization}")
    private String header;

    @Value("${auth.jwt.exp:#{5*60}}")
    private int exp;

    @Value("${auth.jwt.secret:anon}")
    private String secret;

    @Value("${auth.jwt.prefix:Bearer }")
    private String prefix;
}
