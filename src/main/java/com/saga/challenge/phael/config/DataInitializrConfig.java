package com.saga.challenge.phael.config;

import com.saga.challenge.phael.enums.PerfilUsuario;
import com.saga.challenge.phael.model.Perfil;
import com.saga.challenge.phael.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataInitializrConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PerfilService perfilService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if (perfilService.count() == 0) {
            var funcaoAdm = new Perfil(1, PerfilUsuario.ROLE_ADMINISTRADOR.name());
            var funcaoVisitante = new Perfil(2, PerfilUsuario.ROLE_VISITANTE.name());
            perfilService.save(funcaoAdm);
            perfilService.save(funcaoVisitante);
        }
    }
}
