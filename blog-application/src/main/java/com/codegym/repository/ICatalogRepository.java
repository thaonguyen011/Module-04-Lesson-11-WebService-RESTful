package com.codegym.repository;

import com.codegym.model.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICatalogRepository extends PagingAndSortingRepository<Catalog, Long> {
}
