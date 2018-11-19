package util;

import connection.ConnectionManager;
import dao.PaymentDaoImpl;
import dto.PaymentFilterDto;
import model.Currency;
import model.Payment;
import model.PaymentType;


import java.time.LocalDate;
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
