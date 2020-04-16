/**
 * 
 */
package vn.com.fobelife.component.user.repository;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.user.entity.User;

/**
 * @author ahuynh
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAndActiveIsTrue(String email);
    User findByEmail(String email);
    User findByUsernameAndActiveIsTrue(String username);
    User findByUsername(String username);
}
