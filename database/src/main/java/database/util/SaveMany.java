package database.util;

import database.connection.ConnectionManager;
import database.dao.PaymentDaoImpl;
import database.dto.PaymentFilterDto;
import database.model.Currency;
import database.model.Payment;


import java.util.Arrays;
import java.util.List;

public class SaveMany {

    public static void main(String[] args) {

        PaymentFilterDto dto = PaymentFilterDto.builder()
                .currency(Currency.USD)
                .paymentType("0")
        //        .paymentDate(LocalDate.of(2018, 11, 11))
                .build();

        List<Payment> payments = PaymentDaoImpl.getInstance().findFiltered(dto);
        payments.forEach(System.out::println);

        List<Currency> currs = Arrays.asList(Currency.values());
        System.out.println(currs.get(0).getValue());


        ConnectionManager.getFactory().close();
    }

}
