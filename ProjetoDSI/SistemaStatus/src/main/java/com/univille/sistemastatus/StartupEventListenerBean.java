package com.univille.sistemastatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.univille.sistemastatus.model.UsuarioLogin;
import com.univille.sistemastatus.repository.UsuarioLoginRepository;

@Component
public class StartupEventListenerBean {
    @Autowired
    private UsuarioLoginRepository usuarioLoginRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(usuarioLoginRepository.findByUsername("user") == null) {
            UsuarioLogin user = new UsuarioLogin();
            user.setUsername("user");
            user.setPassword("user");
            user.setRole("ROLE_USER");
            usuarioLoginRepository.save(user);
        }


    }
}