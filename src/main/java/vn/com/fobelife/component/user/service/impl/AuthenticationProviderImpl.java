package vn.com.fobelife.component.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("***** Authenticate Provider");
        Object name = authentication.getName();
        Object password = authentication.getCredentials();
        if ("admin".equals(name) && "admin@123".equals(password)) {
            Authentication authentication1 = new UsernamePasswordAuthenticationToken(name, password, getRoles(name.toString()));
            SecurityContextHolder.getContext().setAuthentication(authentication1);
            return authentication1;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<SimpleGrantedAuthority> getRoles(String username) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String roleName = username.startsWith("admin") ? "ADMIN" : "USER";
        authorities.add(new SimpleGrantedAuthority(roleName));
        return authorities;
    }
}
