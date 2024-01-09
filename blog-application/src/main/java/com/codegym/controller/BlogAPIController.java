package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Catalog;
import com.codegym.service.IBlogService;
import com.codegym.service.ICatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/blogs")
public class BlogAPIController {

    private final IBlogService blogService;

    private final ICatalogService catalogService;

    public BlogAPIController(IBlogService blogService, ICatalogService catalogService) {
        this.blogService = blogService;
        this.catalogService = catalogService;
    }

    @ModelAttribute("catalogs")
    private Iterable<Catalog> catalog() {
        return catalogService.findAll();
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Blog>> findAllBlog() {
        List<Blog> blogs = (List<Blog>) blogService.findAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable("id") Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);

        return blogOptional.map(blog -> new ResponseEntity<>(blog, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
