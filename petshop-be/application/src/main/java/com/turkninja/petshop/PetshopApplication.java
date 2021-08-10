package com.turkninja.petshop;

import com.turkninja.petshop.base.EnableExtendedRepositories;
import com.turkninja.petshop.config.SpringSecurityAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.userdetails.User;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
@EnableScheduling
@EnableCaching
@EnableExtendedRepositories
public class PetshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetshopApplication.class, args);
    }

    @Bean
    SpringSecurityAuditorAware auditorProvider() {
        return new SpringSecurityAuditorAware();
    }
}
