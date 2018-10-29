package service;

import dao.BusinessDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Business;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessService {

    private static final BusinessService INSTANCE = new BusinessService();

    public Business getById(Long id) {
        return BusinessDao.getInstance().getById(id);
    }

    public static BusinessService getInstance() {
        return INSTANCE;
    }
}
