//package com.training.timekeeping.security;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//
//import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
//
//public class TokenAuthentication {
//    public static final long EXPIRATIONTIME = 864_000_000;
//    public static final String SECRET = "ThisIsASecret";
//    public static final String TOKEN_PREFIX  = "Bearer";
//    public static final String HEADER_STRING   = "Authorization";
//
//    public static void addAuthentication(HttpServletResponse response, String name) {
////        String JWT = Jwts.builder()
////                .setSubject(email)
////                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
////                .signWith(SignatureAlgorithm.HS512, SECRET)
////                .compact();
////
////        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
//        String token = JWT.create()
//                .withSubject(name)
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
//                .sign(HMAC512(SECRET.getBytes()));
//
//        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);
//    }
//
//    public static Authentication getAuthentication(HttpServletRequest request){
//        String token = request.getHeader(HEADER_STRING);
////        if (token != null) {
////            String email = Jwts.parser()
////                    .setSigningKey(SECRET)
////                    .parseClaimsJws(token.replace(TOKEN_PREFIX, " "))
////                    .getBody()
////                    .getSubject();
////            return (email != null) ?
////                    new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList()) :
////                    null;
////        }
////        return null;
//
//        if (token != null) {
//            String email = JWT.require(HMAC512(SECRET.getBytes()))
//                    .build()
//                    .verify(token.replace(TOKEN_PREFIX, " "))
//                    .getSubject();
//
//            return (email != null) ?
//                    new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>()) :
//                    null;
//        }
//        return null;
//    }
//
//}
