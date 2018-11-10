package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")

@Entity
@Table(name = "employee", schema = "ibank")
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends User {

    @Column(name = "division_id")
    private Integer divisionId;

    public Employee(String name, String loginName, String password, Integer divisionId, Role role) {
        super(name, loginName, password, role);
        this.divisionId = divisionId;
    }
}
