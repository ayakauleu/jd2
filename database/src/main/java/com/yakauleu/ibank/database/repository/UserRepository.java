package com.yakauleu.ibank.database.repository;

import com.yakauleu.ibank.database.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByLoginName(String loginName);

    long count();

}
