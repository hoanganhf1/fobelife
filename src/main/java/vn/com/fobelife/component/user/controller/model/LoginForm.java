/**
 * 
 */
package vn.com.fobelife.component.user.controller.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ahuynh
 *
 */
@Getter
@Setter
public class LoginForm {
    
    public static final String NAME = "loginForm";

    private String username;
    private String password;
    private String submit = "Log In";
}
