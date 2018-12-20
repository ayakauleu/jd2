package com.yakauleu.ibank.service;

import com.yakauleu.ibank.database.dto.PersonRegisterDto;
import com.yakauleu.ibank.database.model.Person;
import com.yakauleu.ibank.database.model.User;
import com.yakauleu.ibank.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getByLogin(String login) {
        return userRepository.findByLoginName(login).get();
    }

    public User createNew(PersonRegisterDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User newUser = new Person(dto.getPhone(), dto.getName(), dto.getLoginName(), encodedPassword);
        return userRepository.save(newUser);
    }


}
