package com.egs.shopapp.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * AuditorAware type class.
 * 
 * @author Nune
 *
 */
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
            .map(e -> e.getAuthentication())
            .map(Authentication::getName);
    }
}