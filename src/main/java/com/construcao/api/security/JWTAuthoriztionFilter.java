package com.construcao.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthoriztionFilter extends BasicAuthenticationFilter {


    public JWTAuthoriztionFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String header = req.getHeader(SecurityConstats.HEADER_STRING);
        if(header == null || !header.startsWith(SecurityConstats.TOKEN_PREFIX)){
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(SecurityConstats.HEADER_STRING);
        if(token == null){
            return  null;
        }
        String user = JWT.require(Algorithm.HMAC512(SecurityConstats.SECRET.getBytes()))
                .build()
                .verify(token.replace(SecurityConstats.TOKEN_PREFIX, ""))
                .getSubject();
        if(user != null){
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

        }
        return null;
    }
}
