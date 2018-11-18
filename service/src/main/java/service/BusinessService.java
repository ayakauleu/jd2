package service;

import database.dao.BusinessDaoImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import database.model.Business;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessService {

    public Business getById(Long id) {
        return (Business) BusinessDaoImpl.getInstance().find(id);
    }

    private static final BusinessService INSTANCE = new BusinessService();

    public static BusinessService getInstance() {
        return INSTANCE;
    }
}
