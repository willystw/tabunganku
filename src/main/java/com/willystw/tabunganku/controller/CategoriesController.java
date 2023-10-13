package com.willystw.tabunganku.controller;

import com.willystw.tabunganku.dto.CategoryDto;
import com.willystw.tabunganku.dto.request.AddCategoryRequest;
import com.willystw.tabunganku.dto.response.AddCategoryResponse;
import com.willystw.tabunganku.dto.response.GetCategoryDetailResponse;
import com.willystw.tabunganku.dto.response.GetCategoryListResponse;
import com.willystw.tabunganku.mapper.CategoryMapper;
import com.willystw.tabunganku.model.Category;
import com.willystw.tabunganku.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "${cross.origins}")
@RestController
@RequestMapping("/users/{userId}/categories")
public class CategoriesController implements ICategoriesController {
  private final CategoryService categoryService;

  public CategoriesController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  @PostMapping("/add")
  public AddCategoryResponse insertNewCategory(
      @PathVariable("userId") long userId,
      @RequestBody AddCategoryRequest request) {
    Long categoryId = categoryService.addNewCategory(
        userId,
        request.getName(),
        request.getType()
    );

    AddCategoryResponse response = new AddCategoryResponse();
    if (categoryId != null) {
      response.setCategoryId(categoryId);
    } else {
      response.setMessage("Fail to create new category");
    }

    return response;
  }

  @Override
  @GetMapping("/list")
  public GetCategoryListResponse getCategoryListByUserId(@PathVariable Long userId) {
    List<Category> categories = categoryService.getCategoryList(userId);

    GetCategoryListResponse response = new GetCategoryListResponse();
    List<CategoryDto> categoryDtoList = new ArrayList<>();

    for (Category cat: categories) {
      CategoryDto dto = CategoryMapper.INSTANCE.categoryToCategoryDto(cat);
      categoryDtoList.add(dto);
    }
    response.setCategories(categoryDtoList);

    return response;
  }

  @Override
  @GetMapping("/get/{categoryId}")
  public GetCategoryDetailResponse getCategoryDetail(
      @PathVariable Long userId,
      @PathVariable Long categoryId) {
    GetCategoryDetailResponse response = new GetCategoryDetailResponse();

    Category category = categoryService.getById(categoryId, userId);
    if (category != null) {

      CategoryDto dto = CategoryMapper.INSTANCE.categoryToCategoryDto(category);
      response.setData(dto);
    } else {
      response.setMessage("Category not found.");
    }

    return response;
  }
}
