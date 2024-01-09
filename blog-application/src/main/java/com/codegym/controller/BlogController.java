package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Catalog;
import com.codegym.service.IBlogService;
import com.codegym.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICatalogService catalogService;

    @ModelAttribute("catalogs")
    private Iterable<Catalog> catalog() {
        return catalogService.findAll();
    }

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/blog/index");
        Pageable pageable = PageRequest.of(0, 1);
        Page<Blog> blogs = blogService.findAll(pageable);
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("blog") Blog blog, BindingResult bindingResult, RedirectAttributes redirect) {
        System.out.println("error: " + bindingResult);
        System.out.println("blog: " + blog);
        blogService.save(blog);
        redirect.addFlashAttribute("success", "Add blog successfully");
        return "redirect:/blogs";
    }


    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/update");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("success", "Update blog successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/view")
    public ModelAndView showViewForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }


    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(Blog blog, RedirectAttributes redirect) {
        blogService.remove(blog.getId());
        redirect.addFlashAttribute("success", "Delete blog successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") String search) {
        Iterable<Blog> blogs = blogService.findAllContainingTitleOrContainingAuthor(search.trim(), search.trim());
        ModelAndView modelAndView = new ModelAndView("/blog/index");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/{catalogId}")
    public ModelAndView searchByCatalog(@PathVariable("catalogId") Long catalogId) {
        Sort sort = Sort.by(Sort.Order.asc("title"));
        Pageable pageable = PageRequest.of(0, 1, sort);
        Optional<Catalog> catalog = catalogService.findById(catalogId);
        Iterable<Blog> blogs = blogService.findByCatalog(catalog.get(), pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/index");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/page")
    public ModelAndView page(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, 1);
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/index");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

}
