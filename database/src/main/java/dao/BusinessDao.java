package dao;

import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import model.Business;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessDao {

    public Business create(Business business) {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        business.setId((Long) session.save(business));
        return business;
    }

    public Business getById(Long id) {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        return session
                .createQuery("select b from Business b where b.id = :id", Business.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    private static final BusinessDao INSTANCE = new BusinessDao();

    public static BusinessDao getInstance() {
        return INSTANCE;
    }
}
