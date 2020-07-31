package com.saga.challenge.phael.config;

import com.saga.challenge.phael.enums.FuncaoUsuario;
import com.saga.challenge.phael.model.Funcao;
import com.saga.challenge.phael.service.FuncaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataInitializrConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private FuncaoService funcaoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if (funcaoService.count() == 0) {
            var funcaoAdm = new Funcao(1, FuncaoUsuario.ROLE_ADMINISTRADOR.name());
            var funcaoVisitante = new Funcao(2, FuncaoUsuario.ROLE_VISITANTE.name());
            funcaoService.save(funcaoAdm);
            funcaoService.save(funcaoVisitante);
        }
    }
}
