package es.codeurjc.books.services.impl;

import es.codeurjc.books.models.User;
import es.codeurjc.books.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        User user = userRepository.findByNick(nick).orElseThrow(() -> new UsernameNotFoundException(nick));
        return new org.springframework.security.core.userdetails.User(user.getNick(), user.getPassword(), Collections.emptyList());
    }
}
