package database.dao;

import database.dto.PaymentFilterDto;
import database.model.Payment;

import java.util.List;

public interface PaymentDao extends BaseDao<Long, Payment> {

    List<Payment> findFiltered(PaymentFilterDto dto);
}
