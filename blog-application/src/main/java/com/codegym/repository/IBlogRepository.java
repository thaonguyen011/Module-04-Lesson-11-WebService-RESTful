package com.codegym.repository;


import com.codegym.model.Blog;
import com.codegym.model.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Iterable<Blog> findAllByCatalog(Catalog catalog);
    Iterable<Blog> findAllByTitle(String title);
    Iterable<Blog> findByTitleContainingOrAuthorContaining(String title, String author);
    Page<Blog> findByCatalog(Catalog catalog, Pageable pageable);

}
