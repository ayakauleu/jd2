package database.dao;

import database.dto.PaymentFilterDto;
import lombok.Cleanup;
import database.model.Currency;
import database.model.Payment;
import database.model.PaymentType;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

import static database.connection.ConnectionManager.getSession;

public class PaymentDaoImpl extends BaseDaoImpl<Long, Payment> implements PaymentDao {

    private static final PaymentDao INSTANCE = new PaymentDaoImpl();

    public static PaymentDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Payment> findFiltered(PaymentFilterDto dto) {
        @Cleanup Session session = getSession();

        Query<Payment> query = session.createQuery("select p from Payment p where p.paymentDate between :dateFrom and :dateTill and p.currency = :currency"/*and p.paymentType = :paymentType"*/, Payment.class)
                .setParameter("currency", dto.getCurrency())
                .setParameter("dateFrom", dto.getDateFrom())
                .setParameter("dateTill", dto.getDateTill())
                .setMaxResults(dto.getLimit())
                .setFirstResult(dto.getLimit() * (dto.getOffset() - 1));

        return query.list();
    }

    public List<Currency> findAllCurrencies() {
        return Arrays.asList(Currency.values());
    }

    public List<PaymentType> findAllTypes() {
        return Arrays.asList(PaymentType.values());
    }
}
