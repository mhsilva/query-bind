package com.tr.query.bind.querybind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericCollectionServiceImpl {
    @Autowired
    private GenericCollectionRepo repo;
    
    public GenericCollection findByNome(String nome) {
        GenericCollection findByNome = repo.findByNome(nome);
        return findByNome;
    }
    
    public GenericCollection save(GenericCollection collection) {
        return repo.save(collection);
    }
}
