package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true, exclude = "businesses")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")

@Entity
@Table(name = "employee", schema = "ibank")
public class Employee extends User {

    @Column(name = "division_id")
    private Integer divisionId;

    @Column(name = "role_id")
    private Integer roleId;

    public Employee(Integer divisionId, Integer roleId, String name, String loginName, String password) {
        super(name, loginName, password);
        this.divisionId = divisionId;
        this.roleId = roleId;
    }
}
