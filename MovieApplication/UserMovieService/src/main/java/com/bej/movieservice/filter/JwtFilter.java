package com.bej.movieservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authHeader = request.getHeader("Authorization");
        System.out.println("filter token fe:"+authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            throw new ServletException("Missing or Invalid token");
        } else {
            String token = authHeader.substring(7);
            System.out.println(token);

            Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);

            // Parse and validate the token and set the user id from claims in the request header as an attribute.
            filterChain.doFilter(request, response);
        }
    }
}
