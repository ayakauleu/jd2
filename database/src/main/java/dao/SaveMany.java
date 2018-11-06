package dao;

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

import java.math.BigDecimal;

public class SaveMany {

    public static final int UNP = 123456789;

    public static void main(String[] args) {

        cleanSchema();

        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
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

        Business business = new Business("IT Academy", UNP);
        business.getPersons().add((Person) userClient);
        Long businessId = (Long) session.save(business);
        session.flush();
        session.evict(business);
        business = session.find(Business.class, businessId);

        CardAccount cardAccount = new CardAccount("BYN", BigDecimal.ZERO, (Person) userClient);
        cardAccount.getCards().add(new Card("1234567891234561", session.find(CardType.class, "1")));
        cardAccount.getCards().add(new Card("1234567891234562", session.find(CardType.class, "2")));
        cardAccount.getCards().add(new Card("1234567891234563", session.find(CardType.class, "3")));
        cardAccount.getCards().add(new Card("1234567891234564", session.find(CardType.class, "4")));
        Long cardAccountId = (Long) session.save(cardAccount);
     /*   session.flush();
        session.evict(cardAccount);
        cardAccount = session.find(CardAccount.class, cardAccountId);*/


        session.getTransaction().commit();

    }

    private static void cleanSchema() {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.createNativeQuery("delete from ibank.card").executeUpdate();
        session.createNativeQuery("delete from ibank.card_type").executeUpdate();
        session.createNativeQuery("delete from ibank.card_account").executeUpdate();
        session.createNativeQuery("delete from ibank.business_person").executeUpdate();
        session.createNativeQuery("delete from ibank.business").executeUpdate();
        session.createNativeQuery("delete from ibank.person").executeUpdate();
        session.createNativeQuery("delete from ibank.employee").executeUpdate();
        session.createNativeQuery("delete from ibank.users").executeUpdate();

        session.save(CardType.of(1, "Visa Electron"));
        session.save(CardType.of(2, "Visa Classic"));
        session.save(CardType.of(1 + 2, "Mastercard Gold"));
        session.save(CardType.of(2 + 2, "Visa Platinum"));

        session.getTransaction().commit();
    }
}

