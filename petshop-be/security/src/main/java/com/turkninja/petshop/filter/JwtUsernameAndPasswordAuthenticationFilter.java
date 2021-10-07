package com.turkninja.petshop.filter;

import com.turkninja.petshop.api.request.user.UserLoginRequest;
import com.turkninja.petshop.config.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // We use auth manager to validate the user credentials
    private AuthenticationManager authManager;

    private final JwtConfig jwtConfig;

    private final TokenProvider tokenProvider;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig, TokenProvider tokenProvider) {
        this.authManager = authManager;
        this.jwtConfig = jwtConfig;
        this.tokenProvider = tokenProvider;

        // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
        // In our case, we use "/auth/login". So, we need to override the defaults.
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getLoginUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {

            // 1. Get credentials from request
            UserLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);

            // 2. Create auth object (contains credentials) which will be used by auth manager
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getEmail(), creds.getPassword(), Collections.emptyList());

            // 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
            return authManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Upon successful authentication, generate a token.
    // The 'auth' passed to successfulAuthentication() is the current authenticated user.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // Add token to header
        Long now = System.currentTimeMillis();
        String token = tokenProvider.getTokenString(auth, now);
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);

        // Add refrsh token to header
        now = System.currentTimeMillis();
        String refreshToken = tokenProvider.getRefreshTokenString(auth, now);
        response.addHeader(jwtConfig.getRefreshHeader(), jwtConfig.getPrefix() + refreshToken);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Login success");
        response.getWriter().flush();
    }

}