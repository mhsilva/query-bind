package com.tr.query.bind.querybind.service;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenericCollectionRepo extends MongoRepository<GenericCollection, String> {
    public GenericCollection findByNome(String nome);
}
