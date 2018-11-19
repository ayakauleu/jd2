package dao;

import dto.PaymentFilterDto;
import model.Payment;

import java.util.List;

public interface PaymentDao extends BaseDao<Long, Payment> {

    List<Payment> findFiltered(PaymentFilterDto dto);
}
