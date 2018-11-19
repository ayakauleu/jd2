import dao.PaymentDaoImpl;
import lombok.Cleanup;
import model.Currency;
import model.Payment;
import model.PaymentType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class PaymentDaoTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void afterClass() {
        FACTORY.close();
    }

    @BeforeClass
    public static void beforeClass() {

        @Cleanup Session session = FACTORY.openSession();

        session.beginTransaction();
        Payment payment1 = new Payment(BigDecimal.valueOf(15), "+375297753577", LocalDate.now(), PaymentType.MOBILE, Currency.BYN, null);
        session.save(payment1);
        Payment payment2 = new Payment(BigDecimal.valueOf(15), "+375297753577", LocalDate.now(), PaymentType.MOBILE, Currency.BYN, null);
        session.save(payment2);

        session.getTransaction().commit();
    }


    @Test
    public void paymentSaveAndFindTest() {
        @Cleanup Session session = FACTORY.openSession();
        Payment payment = new Payment(BigDecimal.valueOf(15), "+375297753577", LocalDate.now(), PaymentType.MOBILE, Currency.BYN, null);
        Long id = PaymentDaoImpl.getInstance().save(payment);
        assertNotNull(id);
        session.evict(payment);
        Payment paymentDb = PaymentDaoImpl.getInstance().find(id);
        assertNotNull(paymentDb);
    }


    @Test
    public void paymentFindAllTest() {
        @Cleanup Session session = FACTORY.openSession();

        List<Payment> payments = PaymentDaoImpl.getInstance().findAll();
        Assert.assertEquals(payments.size(), 2);
    }

    @Test
    public void paymentUpdate() {
        @Cleanup Session session = FACTORY.openSession();

        Payment payment1 = new Payment(BigDecimal.valueOf(15), "+375297753577", LocalDate.now(), PaymentType.MOBILE, Currency.BYN, null);
        session.save(payment1);
        session.evict(payment1);

        Payment paymentDb = PaymentDaoImpl.getInstance().find(1L);
        paymentDb.setAmount(BigDecimal.ONE);
        PaymentDaoImpl.getInstance().update(paymentDb);
        session.evict(paymentDb);
        Payment paymentUpdated = PaymentDaoImpl.getInstance().find(1L);
        Assert.assertEquals(paymentUpdated.getAmount(), BigDecimal.ONE);
    }

    @Test
    public void paymentDeleteTest() {
        @Cleanup Session session = FACTORY.openSession();
        Payment paymentDb = PaymentDaoImpl.getInstance().find(1L);
        PaymentDaoImpl.getInstance().delete(paymentDb);
        List<Payment> payments = PaymentDaoImpl.getInstance().findAll();
        Assert.assertEquals(payments.size(), 2);
    }

}