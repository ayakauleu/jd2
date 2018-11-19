package com.yakauleu.ibank.database.util;

import com.yakauleu.ibank.database.model.Card;
import com.yakauleu.ibank.database.model.Currency;
import com.yakauleu.ibank.database.model.Payment;
import com.yakauleu.ibank.database.model.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DatabaseHelper {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public DatabaseHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void cleanDatabase() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Payment").executeUpdate();
        entityManager.createQuery("delete from Card").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void prepareData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Card card1 = Card.of("1234", null, null);
        Payment payment1 = new Payment(BigDecimal.valueOf(15), "+375297753577", LocalDate.now(), PaymentType.MOBILE, Currency.BYN, null);
        Payment payment2 = new Payment(BigDecimal.valueOf(15), "+375297753577", LocalDate.now(), PaymentType.MOBILE, Currency.BYN, null);

        entityManager.persist(payment1);
        entityManager.persist(payment2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}