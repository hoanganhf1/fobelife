package vn.com.fobelife.component.user.service;

/**
 * 
 * @author ahuynh
 * 2020-01-01
 */

import vn.com.fobelife.component.user.dto.UserDto;

public interface UserService {

    Boolean create(UserDto userDto) throws Exception;
    UserDto getCurrent() throws Exception;
    UserDto getUserByEmail(String email) throws Exception;
    UserDto findByUsername(String username) throws Exception;
    Boolean updateLoginDate(String email) throws Exception;
}
