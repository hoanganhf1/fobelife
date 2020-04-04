package vn.com.fobelife.component.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.user.entity.User;
import vn.com.fobelife.component.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("***** Load User by {} *****", username);
        User user = userRepo.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(getRoles(user.getEmail()));
        UserDetails userDetails = buildUserDetails(user.getUsername(), user.getPassword(), authorities);
        Authentication authentication= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userDetails;
    }

    private UserDetails buildUserDetails(String username, String password, List<GrantedAuthority> authority) {
        return new org.springframework.security.core.userdetails.User(username, password,
                authority);
    }

    private List<SimpleGrantedAuthority> getRoles(String username) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String roleName = username.startsWith("admin") ? "ADMIN" : "USER";
        authorities.add(new SimpleGrantedAuthority(roleName));
        return authorities;
    }
}
