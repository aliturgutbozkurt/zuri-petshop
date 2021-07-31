package com.turkninja.petshop.filter;

import com.turkninja.petshop.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    private final TokenProvider tokenProvider;

    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig, TokenProvider tokenProvider) {
        this.jwtConfig = jwtConfig;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {


        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        String header = request.getHeader(jwtConfig.getHeader());

        if(request.getRequestURI().equals("/auth/refresh")){
            header = request.getHeader(jwtConfig.getRefreshHeader());
         }

        // 2. validate the header and check the prefix
        if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response);  		// If not valid, go to the next filter.
            return;
        }

        // If there is no token provided and hence the user won't be authenticated.
        // It's Ok. Maybe the user accessing a public path or asking for a token.

        // All secured paths that needs a token are already defined and secured in config class.
        // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.

        // 3. Get the token
        String token = header.replace(jwtConfig.getPrefix(), "");



        try {	// exceptions might be thrown in creating the claims if for example the token is expired

            // 4. Validate the token
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if(username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims.get("authorities");

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

                // 6. Authenticate the user
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
                if(request.getRequestURI().equals("/api/v1/auth/refresh")){
                    refreshTokens(response, auth);
                }
            }

        } catch (Exception e) {
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }

        // go to the next filter in the filter chain
        chain.doFilter(request, response);
    }

    private void refreshTokens(HttpServletResponse response, UsernamePasswordAuthenticationToken auth)  throws IOException {
        Long now = System.currentTimeMillis();
        String token = tokenProvider.getTokenString(auth, now);
        String refreshToken = tokenProvider.getRefreshTokenString(auth, now);
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
        response.addHeader(jwtConfig.getRefreshHeader(), jwtConfig.getPrefix() + refreshToken );
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
        response.addHeader(jwtConfig.getRefreshHeader(), jwtConfig.getPrefix() + refreshToken);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Refresh success");
        response.getWriter().flush();
    }

}