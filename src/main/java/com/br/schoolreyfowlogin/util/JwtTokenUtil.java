package com.br.schoolreyfowlogin.util;

import com.br.schoolreyfowlogin.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;

import static java.lang.String.format;

@Component
public class JwtTokenUtil implements Serializable {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP512lm2123113513nlm15161515";

    public String generateAccessToken(UserModel userModel) {
        return Jwts.builder()
                .setSubject(format("%s", userModel.getId()))
                .setIssuer(userModel.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        var keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
