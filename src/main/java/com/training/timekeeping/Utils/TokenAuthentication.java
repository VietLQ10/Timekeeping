package com.training.timekeeping.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class TokenAuthentication {
    public static final long EXPIRATIONTIME = 864_000_000;
    public static final String SECRET = "ThisIsASecret";
    public static final String TOKEN_PREFIX  = "Bearer";
    public static final String HEADER_STRING   = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String email) {
        String JWT = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String email = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, " "))
                    .getBody()
                    .getSubject();
            return (email != null) ?
                    new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList()) :
                    null;
        }
        return null;
    }

}
