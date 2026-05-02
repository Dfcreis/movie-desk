package com.movieDesk.MovieDesk.controller;

import com.movieDesk.MovieDesk.controller.request.CategoryRequest;
import com.movieDesk.MovieDesk.controller.response.CategoryResponse;
import com.movieDesk.MovieDesk.entity.Category;
import com.movieDesk.MovieDesk.mapper.CategoryMapper;
import com.movieDesk.MovieDesk.services.CategoryServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieDesk/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServices categoryServices;


    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryServices.getAllCategories().stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList());
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request) {

       Category category = CategoryMapper.toCategory(request);
       Category saveCategory = categoryServices.createCategory(category);

       return ResponseEntity.status(HttpStatus.CREATED)
               .body(CategoryMapper
               .toCategoryResponse(saveCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
       return categoryServices.getCategoryById(id).map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryServices.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
