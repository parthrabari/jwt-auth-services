package com.mastercard.authservice.Controller;

import com.mastercard.authservice.auth.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private TokenInfo tokenInfo;

    //it reaches here so token is valid
    @GetMapping("/auth/refresh")
    public ResponseEntity get(@RequestHeader("Authorization") String auth) {

        HttpHeaders responseHeaders = new HttpHeaders();

        String oldToken = auth.replace(tokenInfo.getPrefix(), "");

        Claims claims = Jwts.parser()
                .setSigningKey(tokenInfo.getSecret().getBytes())
                .parseClaimsJws(oldToken)
                .getBody();
        String username = claims.getSubject();

        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken authn = new UsernamePasswordAuthenticationToken(
                username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        Long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(username)
                .claim("authorities", authn.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + tokenInfo.getExp() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, tokenInfo.getSecret().getBytes())
                .compact();
        responseHeaders.set("Authorization", tokenInfo.getPrefix() + token);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Token refreshed, please check the headers");
    }
}
