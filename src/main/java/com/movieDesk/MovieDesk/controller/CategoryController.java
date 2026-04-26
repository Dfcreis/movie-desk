package com.movieDesk.MovieDesk.controller;

import com.movieDesk.MovieDesk.entity.Category;
import com.movieDesk.MovieDesk.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Category createCategory(@RequestBody Category category){
        return categoryServices.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        Optional<Category>optionalCategory = categoryServices.getCategoryById(id);
        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        return null;
    }


    @DeleteMapping("delete/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        categoryServices.deleteCategoryById(id);
    }





}
