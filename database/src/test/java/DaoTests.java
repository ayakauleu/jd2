import lombok.Cleanup;
import model.Business;
import model.Card;
import model.CardAccount;
import model.CardType;
import model.Employee;
import model.Person;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Set;

public class DaoTests {

    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @After
    public void finish() {
        sessionFactory.close();
    }

    @Test
    public void testSaveAndFind() {

        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        User userAdmin = new Employee(1, 1, "Andrei Petrov", "apetrov", "123");
        Long userAdminId = (Long) session.save(userAdmin);
        session.evict(userAdmin);
        userAdmin = session.find(User.class, userAdminId);

        User userClient = new Person("+375297894561", "Andrei Sidorov", "asidorov", "123");
        Long userClientId = (Long) session.save(userClient);
        session.evict(userClient);
        userClient = session.find(User.class, userClientId);

        Business business = new Business("IT Academy", 123456789);
        business.getPersons().add((Person) userClient);
        Long businessId = (Long) session.save(business);
        session.flush();
        session.evict(business);
        business = session.find(Business.class, businessId);

        CardAccount cardAccount = new CardAccount("BYN", BigDecimal.ZERO, (Person) userClient);
        cardAccount.getCards().add(new Card("1234567891234568", session.find(CardType.class, 1)));
        cardAccount.getCards().add(new Card("1234567891234568", session.find(CardType.class, 2)));
        cardAccount.getCards().add(new Card("1234567891234568", session.find(CardType.class, 3)));
        cardAccount.getCards().add(new Card("1234567891234568", session.find(CardType.class, 4)));
        Long cardAccountId = (Long) session.save(cardAccount);
        session.flush();
        session.evict(cardAccount);
        cardAccount = session.find(CardAccount.class, cardAccountId);

        session.getTransaction().commit();
    }
}

