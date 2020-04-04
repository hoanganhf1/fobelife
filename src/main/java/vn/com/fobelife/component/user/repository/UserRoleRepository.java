/**
 * 
 */
package vn.com.fobelife.component.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.user.entity.User;
import vn.com.fobelife.component.user.entity.UserRole;
import vn.com.fobelife.component.user.entity.UserRole.UserRoleId;

/**
 * @author ahuynh
 *
 */
public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {

    List<UserRole> findByUser(User user);
}
