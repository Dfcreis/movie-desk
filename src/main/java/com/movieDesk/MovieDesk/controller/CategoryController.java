package com.movieDesk.MovieDesk.controller;

import com.movieDesk.MovieDesk.entity.Category;
import com.movieDesk.MovieDesk.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movieDesk/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServices categoryServices;


    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryServices.getAllCategories();
    }

    @PostMapping("/create")
    public Category createCategory(Category category){
        return categoryServices.createCategory(category);
    }



}
