/**
 * 
 */
package vn.com.fobelife.component.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.user.dto.UserDto;
import vn.com.fobelife.component.user.entity.User;
import vn.com.fobelife.component.user.repository.UserRepository;
import vn.com.fobelife.component.user.service.UserService;

/**
 * @author ahuynh
 *
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passEncode;

    @Override
    @Transactional(readOnly = false)
    public Boolean create(UserDto userDto) throws Exception {
        log.info("***** Create new user *****");
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passEncode.encode(userDto.getPassword()));
        user.setActive(true);
        user.setPoint(0);
        user = userRepo.save(user);
        return Boolean.TRUE;
    }

    @Override
    public UserDto getCurrent() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return findByUsername(currentUserName);
        }
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) throws Exception {
        User user = userRepo.findByEmailAndActiveIsTrue(email);
        return applyDto(user);
    }

    private UserDto applyDto(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPoint(user.getPoint());
        return dto;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean updateLoginDate(String username) throws Exception {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            user.setLastLoginDate(new Date());
            userRepo.save(user);
        }
        return Boolean.TRUE;
    }

    @Override
    public UserDto findByUsername(String username) throws Exception {
        User user = userRepo.findByUsername(username);
        return applyDto(user);
    }
}
