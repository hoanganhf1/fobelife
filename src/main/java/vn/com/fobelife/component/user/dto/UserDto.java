/**
 * 
 */
package vn.com.fobelife.component.user.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ahuynh
 *
 */
@Getter
@Setter
public class UserDto {

    private String email;
    private String password;
    private String username;
    private List<RoleDto> roles;
    private Integer point;
}
