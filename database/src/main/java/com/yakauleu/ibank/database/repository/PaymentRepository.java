package com.yakauleu.ibank.database.repository;

import com.yakauleu.ibank.database.model.Currency;
import com.yakauleu.ibank.database.model.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findByCurrencyAndPaymentDateBetween(Currency currency, LocalDate dateFrom, LocalDate dateTill, Pageable pageable);

    List<Payment> findAll();

}
