package com.saga.challenge.phael.service;

import com.saga.challenge.phael.core.AbstractService;
import com.saga.challenge.phael.model.Perfil;
import com.saga.challenge.phael.repository.IPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService extends AbstractService<Perfil, Integer> {

    @Autowired
    public PerfilService(IPerfilRepository repository) {
        super(repository);
    }
}
