package database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")

@Entity
@Table(name = "card", schema = "ibank")
public class Card extends BaseEntity<Long> {

    @Column(name = "card_number")
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private CardAccount cardAccount;

}
