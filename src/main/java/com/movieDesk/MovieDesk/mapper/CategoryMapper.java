package com.movieDesk.MovieDesk.mapper;

import com.movieDesk.MovieDesk.controller.request.CategoryRequest;
import com.movieDesk.MovieDesk.controller.response.CategoryResponse;
import com.movieDesk.MovieDesk.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
       return CategoryResponse.builder()
               .id(category.getId())
               .name(category.getName())
               .build();
    }




}
