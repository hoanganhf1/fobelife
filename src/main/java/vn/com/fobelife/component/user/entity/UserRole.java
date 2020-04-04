package vn.com.fobelife.component.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_ROLE", indexes = { @Index(columnList = "USER_ID, ROLE_ID", unique = true) })
@Getter
@Setter
public class UserRole implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3331506697014214608L;

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private Role role;

    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Embeddable
    @Getter
    @Setter
    public static class UserRoleId implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = -9209345006380383951L;

        @JoinColumn(name = "USER_ID", nullable = false)
        private Long userId;

        @JoinColumn(name = "ROLE_ID", nullable = false)
        private Long roleId;

        protected UserRoleId() {

        }

        public UserRoleId(Long userId, Long roleId) {
            this.userId = userId;
            this.roleId = roleId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            UserRoleId that = (UserRoleId) o;
            return Objects.equals(this.userId, that.userId) && Objects.equals(this.roleId, that.roleId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.userId, this.roleId);
        }
    }
}
