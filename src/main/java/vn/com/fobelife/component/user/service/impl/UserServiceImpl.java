/**
 * 
 */
package vn.com.fobelife.component.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.user.dto.RoleDto;
import vn.com.fobelife.component.user.dto.UserDto;
import vn.com.fobelife.component.user.entity.Role;
import vn.com.fobelife.component.user.entity.User;
import vn.com.fobelife.component.user.entity.UserRole;
import vn.com.fobelife.component.user.repository.RoleRepository;
import vn.com.fobelife.component.user.repository.UserRepository;
import vn.com.fobelife.component.user.repository.UserRoleRepository;
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
    private UserRoleRepository userRoleRepo;

    @Autowired
    private RoleRepository roleRepo;

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
        List<UserRole> userRoles = new ArrayList<>();
        for(RoleDto r : userDto.getRoles()) {
            Role role = roleRepo.findByName(r.getName());
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoles.add(userRole);
        }
        userRoleRepo.saveAll(userRoles);
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
        List<UserRole> userRoles = userRoleRepo.findByUser(user);
        return applyDto(user, userRoles);
    }

    private UserDto applyDto(User user, List<UserRole> roles) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRoles(applyDto(roles));
        dto.setPoint(user.getPoint());
        return dto;
    }

    private RoleDto applyDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        return dto;
    }

    private List<RoleDto> applyDto(List<UserRole> roles) {
        List<RoleDto> dtos = new ArrayList<>();
        roles.forEach(r -> dtos.add(applyDto(r.getRole())));
        return dtos;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean updateLoginDate(String username) throws Exception {
        User user = userRepo.findByUsername(username);
        user.setLastLoginDate(new Date());
        userRepo.save(user);
        return Boolean.TRUE;
    }

    @Override
    public UserDto findByUsername(String username) throws Exception {
        User user = userRepo.findByUsername(username);
        List<UserRole> userRoles = userRoleRepo.findByUser(user);
        return applyDto(user, userRoles);
    }
}
