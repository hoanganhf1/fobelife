/**
 * 
 */
package vn.com.fobelife.component.user.repository;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.user.entity.Role;

/**
 * @author ahuynh
 *
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
