package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Catalog;
import com.codegym.service.IBlogService;
import com.codegym.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogAPIController {

    @Autowired
    private ICatalogService catalogService;

    @Autowired
    private IBlogService blogService;

    @GetMapping("")
    public ResponseEntity<Iterable<Catalog>> findAllCatalog() {
        List<Catalog> catalogs = (List<Catalog>) catalogService.findAll();

        if (catalogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(catalogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Blog>> findAllBlogByCatalog(@PathVariable("id") Long id) {
        Optional<Catalog> catalogOptional = catalogService.findById(id);

        if (!catalogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Blog> blogs = (List<Blog>) blogService.findAllByCatalog(catalogOptional.get());
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

}
