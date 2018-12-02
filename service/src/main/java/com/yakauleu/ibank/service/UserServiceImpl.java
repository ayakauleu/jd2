package com.yakauleu.ibank.service;


import com.yakauleu.ibank.service.converter.UserDetailsConverter;
import com.yakauleu.ibank.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsConverter detailsConverter;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByLoginName(name)
                .map(detailsConverter::convert)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
