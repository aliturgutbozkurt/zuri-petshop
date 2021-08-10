package com.turkninja.petshop.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

/**
 * @author ali turgut bozkurt
 * Created at 8/10/2021
 */

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(loggedInUser.getName());
    }
}