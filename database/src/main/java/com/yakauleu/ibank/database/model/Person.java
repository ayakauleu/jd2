package com.yakauleu.ibank.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true, exclude = "businesses")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")

@Entity
@Table(name = "person", schema = "ibank")
@PrimaryKeyJoinColumn(name = "id")
public class Person extends User {

    @Column(name = "phone")
    private String phone;

    @ManyToMany
    @JoinTable(name = "business_person", schema = "ibank",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "business_id")
    )
    private Set<Business> businesses = new HashSet<>();

    public Person(String phone, String name, String loginName, String password) {
        super(name, loginName, password, Role.CLIENT);
        this.phone = phone;
    }
}
