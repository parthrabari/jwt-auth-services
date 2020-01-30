package com.mastercard.apigateway.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenInfo tokenInfo;

    public JwtTokenAuthenticationFilter(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader(tokenInfo.getHeader());

        if (header == null || !header.startsWith(tokenInfo.getPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(tokenInfo.getPrefix(), "");

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenInfo.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            if (username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims.get("authorities");

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            if(e instanceof ExpiredJwtException) {
                response.getWriter().write("Your token has expired.");
            }
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }

}