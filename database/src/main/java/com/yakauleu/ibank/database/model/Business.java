package com.yakauleu.ibank.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = "persons")

@Entity
@Table(name = "business", schema = "ibank")
public class Business extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "unp")
    private Integer unp;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "business_person", schema = "ibank",
            joinColumns = @JoinColumn(name = "business_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> persons = new ArrayList<>();

    public Business(String name, Integer unp) {
        this.name = name;
        this.unp = unp;
    }
}
