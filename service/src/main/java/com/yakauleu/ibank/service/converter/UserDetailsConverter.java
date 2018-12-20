package com.yakauleu.ibank.service.converter;

import com.yakauleu.ibank.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter implements Converter<User, UserDetails> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails convert(User user) {

        UserDetails springUser = org.springframework.security.core.userdetails.User.builder()
                .username(user.getLoginName())
                .password(user.getPassword())
//                .password("{noop}" + user.getPassword())
                .authorities(user.getRole().name())
//                .passwordEncoder(new BCryptPasswordEncoder()::encode)
                .build();

        return springUser;
    }
}
