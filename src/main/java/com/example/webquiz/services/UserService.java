package com.example.webquiz.services;

import com.example.webquiz.dto.MyUserDetails;
import com.example.webquiz.entities.User;
import com.example.webquiz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found user with email:" + email));

        return user.map(MyUserDetails::new).get();
    }
}
