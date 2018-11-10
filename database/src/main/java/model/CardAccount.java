package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "card_account", schema = "ibank")
public class CardAccount extends BaseEntity<Long> {

    private String currency;

    private BigDecimal rest;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "cardAccount", cascade = CascadeType.ALL)
    private Set<Card> cards = new HashSet<>();

    public CardAccount(String currency, BigDecimal rest, Person person) {
        this.currency = currency;
        this.rest = rest;
        this.person = person;
    }
}
