package com.codegym.service.impl;

import com.codegym.model.Blog;
import com.codegym.model.Catalog;
import com.codegym.repository.IBlogRepository;
import com.codegym.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Iterable<Blog> findAll(Sort sort) {
        return blogRepository.findAll(sort);
    }

    @Override
    public Iterable<Blog> findAllByCatalog(Catalog catalog) {
        return blogRepository.findAllByCatalog(catalog);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Iterable<Blog> findAllByTitle(String tile) {
        return blogRepository.findAllByTitle(tile);
    }

    @Override
    public Iterable<Blog> findAllContainingTitleOrContainingAuthor(String title, String author) {
        return blogRepository.findByTitleContainingOrAuthorContaining(title, author);
    }

    @Override
    public Page<Blog> findByCatalog(Catalog catalog, Pageable pageable) {
        return blogRepository.findByCatalog(catalog, pageable);
    }
}
