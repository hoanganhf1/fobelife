package vn.com.fobelife.component.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.user.controller.model.SignupForm;
import vn.com.fobelife.component.user.dto.UserDto;
import vn.com.fobelife.component.user.service.UserService;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String getSignup(HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** SIGNUP PAGE *****");
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupForm form, HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** PERFORM SIGNUP *****");
        try {
            UserDto dto = new UserDto();
            dto.setEmail(form.getEmail());
            dto.setUsername(form.getUsername());
            dto.setPassword(form.getPassword());
            userService.create(dto);
        } catch (Exception e) {
            log.error("Create user: ", e);
        }
        return "cart";
    }

    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest req, HttpServletResponse rep) {
        log.info("********* LOGIN PAGE *****");
        return "login";
    }
}
