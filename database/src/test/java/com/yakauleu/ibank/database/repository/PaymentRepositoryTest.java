package com.yakauleu.ibank.database.repository;

import com.yakauleu.ibank.database.model.Currency;
import com.yakauleu.ibank.database.model.Payment;
import com.yakauleu.ibank.database.model.Person;
import com.yakauleu.ibank.database.model.User;
import com.yakauleu.ibank.database.util.DatabaseHelper;
import com.yakauleu.ibank.database.config.TestConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class PaymentRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void check() {
        paymentRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void checkFilter() {
        List<Payment> payments = paymentRepository.findByCurrencyAndPaymentDateBetween(Currency.BYN, LocalDate.now(), LocalDate.now(), PageRequest.of(0, 1));
        System.out.println(payments.get(0).toString());
        Assert.assertEquals(1, payments.size());
    }

    @Test
    public void testNewUser() {
        User user = new Person("123",  "me", "ayakauleu", "123");
        System.out.println(user);
        user = userRepository.save(user);
  /*      EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);*/
        System.out.println(user);
    }

}