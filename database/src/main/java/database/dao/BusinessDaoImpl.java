package database.dao;

import database.model.Business;

public class BusinessDaoImpl extends BaseDaoImpl<Long, Business> implements BusinessDao {

    private static final BusinessDao INSTANCE = new BusinessDaoImpl();

    public static BusinessDao getInstance() {
        return INSTANCE;
    }

}
