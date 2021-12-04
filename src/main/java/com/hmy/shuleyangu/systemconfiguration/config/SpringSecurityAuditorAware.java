package com.hmy.shuleyangu.systemconfiguration.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware {
    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.ofNullable("hmy").filter(s -> !s.isEmpty());
    }
}
