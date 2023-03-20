package org.iptime.yline.securityjwt.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//모든 요청마다 해당 필터가 걸리도록
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        //헤더에서 토큰 꺼내기 위함
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        //만약 토큰이 없을 경우? 다음 필터로 넘김
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //jwt를 생성
        //7부터 자리는 이유는 위에 "Bearer "인덱스가 6까지이기 때문
        jwt = authHeader.substring(7);

        //todo Jwt에서 userEamil 추출 하는것을 만들어야함
        userEmail = jwtService.extractUsername(jwt);
    }
}
