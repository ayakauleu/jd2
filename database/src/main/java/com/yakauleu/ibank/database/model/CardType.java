package com.yakauleu.ibank.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")

@Entity
@Table(name = "card_type", schema = "ibank")
public class CardType {

    @Id
    @Column
    private Integer id;

    @Column(name = "type_name")
    private String typeName;
}
