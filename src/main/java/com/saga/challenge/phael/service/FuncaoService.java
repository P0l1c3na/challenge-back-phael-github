package com.saga.challenge.phael.service;

import com.saga.challenge.phael.core.AbstractService;
import com.saga.challenge.phael.model.Funcao;
import com.saga.challenge.phael.repository.IFuncaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncaoService extends AbstractService<Funcao, Integer> {

    @Autowired
    public FuncaoService(IFuncaoRepository repository) {
        super(repository);
    }
}
