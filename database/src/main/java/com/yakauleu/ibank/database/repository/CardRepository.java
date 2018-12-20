package com.yakauleu.ibank.database.repository;

import com.yakauleu.ibank.database.model.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findAll();

    @Query(value = "select c.* "
            +
            " from ibank.person p "
            +
            " left join ibank.card_account a on a.person_id = p.id"
            +
            " join ibank.card c on c.account_id = a.id"
            +
            " where p.id = :personId", nativeQuery = true)

    List<Card> findAllByPerson(@Param("personId") Long personId);
}