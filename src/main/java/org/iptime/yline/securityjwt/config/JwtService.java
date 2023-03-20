package org.iptime.yline.securityjwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    //여기 사이트에서 자동으로 키 생성해줌 -> https://www.allkeysgenerator.com
    private static final String SECRET_KEY = "3878214125442A472D4B6150645367566B597033733676397924423F4528482B";

    public String extractUsername(String token) {
        return null;
    }

    //모든 클래임을 추출하기 위한 메서드
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //서명키의 메서드
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
