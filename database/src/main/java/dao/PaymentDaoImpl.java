package dao;

import dto.PaymentFilterDto;
import lombok.Cleanup;
import model.Currency;
import model.Payment;
import model.PaymentType;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

import static connection.ConnectionManager.getSession;

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
