package com.codegym.service.impl;


import com.codegym.model.Catalog;
import com.codegym.repository.ICatalogRepository;
import com.codegym.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogService implements ICatalogService {
    @Autowired
    private ICatalogRepository catalogRepository;

    @Override
    public Iterable<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Optional<Catalog> findById(Long id) {
        return catalogRepository.findById(id);
    }

    @Override
    public void save(Catalog catalog) {
        catalogRepository.save(catalog);
    }

    @Override
    public void remove(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public Iterable<Catalog> findAll(Sort sort) {
        return catalogRepository.findAll(sort);
    }

}
