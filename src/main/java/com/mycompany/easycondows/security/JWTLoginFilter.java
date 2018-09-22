package com.mycompany.easycondows.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.easycondows.user.model.User;
import com.mycompany.easycondows.user.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    MyUserDetailsService userDetailService;

    protected JWTLoginFilter(String url, AuthenticationManager authManager, MyUserDetailsService userDetailService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userDetailService = userDetailService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        User user = new ObjectMapper()
                .readValue(request.getInputStream(), User.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication auth) throws IOException, ServletException {

        UserDetails user = userDetailService.loadUserByUsername(auth.getName());
        Long userId = ((MyUserDetailsService.MyUserPrincipal) user).getUser().getId();

        TokenAuthenticationService.addAuthentication(response, auth.getName(), userId);

    }

}