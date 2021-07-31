package com.turkninja.petshop.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class JwtConfig {

    @Value("${security.jwt.uri}")
    private String Uri;

    @Value("${security.jwt.login.uri}")
    private String loginUri;

    @Value("${security.jwt.header}")
    private String header;

    @Value("${security.jwt.header.refresh}")
    private String refreshHeader;

    @Value("${security.jwt.prefix}")
    private String prefix;

    @Value("${security.jwt.expiration}")
    private long expiration;

    @Value("${security.jwt.refresh.expiration}")
    private long refreshExpiration;

    @Value("${security.jwt.secret}")
    private String secret;

}