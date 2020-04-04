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
public class SignupForm {
    
    public static final String NAME = "signupForm";

    private String username;
    private String email;
    private String password;
    private String btnSignup;
}
