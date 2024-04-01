package com.bej.authentication.security;


import com.bej.authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    public String createToken(User user){
        // Write logic to create the Jwt
        Map<String,Object> claims= new HashMap<>();
      //  claims.put("Email",user.getEmail());
        claims.put("userId",user.getUserId());
        System.out.println("from token "+claims);
        return generateToken(claims, user.getUserId());
    }

    public String generateToken(Map<String,Object> claims,String subject) {
        // Generate the token and set the user id in the claims
        String jwtToken = Jwts.builder().setIssuer("Jayasree").
        setClaims(claims).setSubject(subject).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secretkey")
                .compact();
        return jwtToken;
    }


}
