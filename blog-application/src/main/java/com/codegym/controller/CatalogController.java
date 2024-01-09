package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Catalog;
import com.codegym.service.IBlogService;
import com.codegym.service.ICatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/catalogs")
public class CatalogController {

    private final ICatalogService catalogService;

    private final IBlogService blogService;

    public CatalogController(ICatalogService catalogService, IBlogService blogService) {
        this.catalogService = catalogService;
        this.blogService = blogService;
    }

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/catalog/index");
        Iterable<Catalog> catalogs = catalogService.findAll();
        modelAndView.addObject("catalogs", catalogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/catalog/create");
        modelAndView.addObject("catalog", new Catalog());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(Catalog catalog, RedirectAttributes redirect) {
        catalogService.save(catalog);
        redirect.addFlashAttribute("success", "Add catalog successfully");
        return "redirect:/catalogs";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Optional<Catalog> catalog = catalogService.findById(id);
        if (catalog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/catalog/update");
            modelAndView.addObject("catalog", catalog.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
     }

    @PostMapping("/update")
    public String update(Catalog catalog, RedirectAttributes redirect) {
        catalogService.save(catalog);
        redirect.addFlashAttribute("success", "Update catalog successfully");
        return "redirect:/catalogs";
    }

    @GetMapping("/{id}/view")
    public ModelAndView showViewForm(@PathVariable("id") Long id) {
        Optional<Catalog> catalog = catalogService.findById(id);

        if (!catalog.isPresent()) {
            return new ModelAndView("/error_404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/catalog/view");
            Iterable<Blog> blogs = blogService.findAllByCatalog(catalog.get());
            modelAndView.addObject("catalog", catalog.get());
            modelAndView.addObject("blogs", blogs);
            return modelAndView;

        }
    }


    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/catalog/delete");
        Catalog catalog =  catalogService.findById(id).orElse(null);
        modelAndView.addObject("catalog", catalog);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(Blog blog, RedirectAttributes redirect) {
        catalogService.remove(blog.getId());
        redirect.addFlashAttribute("success", "Delete catalog successfully");
        return "redirect:/catalogs";
    }

}
