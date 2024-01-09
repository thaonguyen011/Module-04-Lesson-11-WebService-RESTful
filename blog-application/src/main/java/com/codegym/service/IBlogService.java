package com.codegym.service;


import com.codegym.model.Blog;
import com.codegym.model.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IBlogService extends IGenerateService<Blog>{
    Iterable<Blog> findAllByCatalog(Catalog catalog);
    Page<Blog> findAll(Pageable pageable);
    Iterable<Blog> findAllByTitle(String tile);
    Iterable<Blog> findAllContainingTitleOrContainingAuthor(String title, String author);
    Page<Blog> findByCatalog(Catalog catalog, Pageable pageable);


}
