/**
 * 
 */
package vn.com.fobelife.component.user.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.user.service.UserService;

/**
 * @author ahuynh
 *
 */
@Service
@Slf4j
@Transactional
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("***** Login Success Handler *****");
        try {
            String username = authentication.getName();
            userService.updateLoginDate(username);
        } catch (Exception e) {
            log.error("Update login date: ", e);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
