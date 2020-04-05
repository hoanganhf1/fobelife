package vn.com.fobelife.component.auditing.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String auditor = SecurityContextHolder.getContext().getAuthentication().getName();
        if (StringUtils.isBlank(auditor)) {
            auditor = "system";
        }
        return Optional.ofNullable(auditor);
    }

}
