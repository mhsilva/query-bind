package com.tr.query.bind.querybind.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericCollectionRepo extends CrudRepository<GenericCollection, Long> {
    public GenericCollection findByName(String name);
}
