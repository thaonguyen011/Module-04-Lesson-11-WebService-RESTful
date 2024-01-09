package com.codegym.service;


import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface IGenerateService<E> {
    Iterable<E> findAll();
    Optional<E> findById(Long id);
    void save(E e);
    void remove(Long id);
    Iterable<E> findAll(Sort sort);

}
