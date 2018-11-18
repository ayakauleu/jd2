package database.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor

@Entity
@Table(name = "users", schema = "ibank")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "loginname")
    private String loginName;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public User(String name, String loginName, String password, Role role) {
        this.name = name;
        this.loginName = loginName;
        this.password = password;
        this.role = role;
    }
}
