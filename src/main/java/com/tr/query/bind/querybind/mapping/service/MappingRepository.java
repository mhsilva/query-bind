package com.tr.query.bind.querybind.mapping.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tr.query.bind.querybind.mapping.entity.Menu;

public interface MappingRepository extends CrudRepository<Menu, Long>{

	public Optional<Menu> findByName(String name);
}
