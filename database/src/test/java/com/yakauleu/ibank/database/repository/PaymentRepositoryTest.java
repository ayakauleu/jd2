package com.yakauleu.ibank.database.repository;

import com.yakauleu.ibank.database.model.Currency;
import com.yakauleu.ibank.database.model.Payment;
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

}