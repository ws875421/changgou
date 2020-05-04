package com.spring.service;

import com.spring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpringDataUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查找DB
        //userRepository.

        System.out.println(" username " + username);

        Optional<com.spring.Dao.User> user = userRepository.findById(username);

        if (user.isPresent()) {
            return User.withUsername(user.get().getUsername()).password(user.get().getPassword()).authorities("p1").build();
        }


        return null;

    }


}
