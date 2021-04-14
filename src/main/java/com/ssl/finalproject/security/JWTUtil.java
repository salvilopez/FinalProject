package com.ssl.finalproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Component
public class JWTUtil {
    private static final String KEY ="salvilopezronda";
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,KEY).compact();

    }

    public boolean validateToken8(String token,UserDetails userDetails){
 return userDetails.getUsername().equals(extractUsername(token))&& !isTokenExpired(token);
    }
public boolean isTokenExpired(String token){
return getClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }
    private Claims getClaims(String token){
    return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}