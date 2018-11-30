package com.univille.sistemastatus.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.univille.sistemastatus.model.UsuarioLogin;
import com.univille.sistemastatus.repository.UsuarioLoginRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
 
    @Autowired
    private UsuarioLoginRepository usuarioLoginRepository;
 
    @Override
    public User loadUserByUsername(String username) {
        UsuarioLogin user = usuarioLoginRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<SimpleGrantedAuthority> listGrants = new ArrayList<>();
        listGrants.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(),user.getPassword(),listGrants);
    }
}