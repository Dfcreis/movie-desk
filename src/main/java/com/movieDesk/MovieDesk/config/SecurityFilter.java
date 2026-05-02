package com.movieDesk.MovieDesk.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenServices tokenServices;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (Strings.isNotEmpty((authorization)) && authorization.startsWith("Bearer")){
            String token = authorization.substring("Bearer".length());


            Optional<JwtUserData> jwtUserData = tokenServices.verifyToken(token);
            if (jwtUserData.isPresent()){
                JwtUserData userData = jwtUserData.get();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userData, null, null
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }


            filterChain.doFilter(request, response);

        }else{
            filterChain.doFilter(request, response);
        }

    }
}
