package com.example.CRUD.jwt_tokens;

import com.example.CRUD.ExcetionHandling.IllegalAuthException;
import com.example.CRUD.UserDto.UserDto;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGeneration {
    Logger logger = LoggerFactory.getLogger(TokenGeneration.class);
    private final static String private_key = "secret private key";
    Long time =System.currentTimeMillis();
    long expiryTime = time+(10*60*1000);
    Date issueDate =new Date(time);
    Date expiryDate = new Date(expiryTime);

    public String generationMethod (UserDto userDto){
        Claims claims= Jwts.claims().setExpiration(expiryDate).setIssuedAt(issueDate).setIssuer(userDto.getName());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,private_key).compact();
    }

    public Claims verifyToken(String authorization){
        try {
            Claims claims = Jwts.parser().setSigningKey(private_key).parseClaimsJws(authorization).getBody();
            return claims;
        }catch (IllegalArgumentException e){
            logger.atInfo();
            logger.atDebug();
            logger.atError();
            throw new IllegalAuthException();
        }catch (MalformedJwtException m){
            throw new IllegalAuthException();
        }
    }
}
