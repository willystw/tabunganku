package com.willystw.tabunganku.controller;

import com.willystw.tabunganku.dto.CategoryDto;
import com.willystw.tabunganku.dto.request.AddCategoryRequest;
import com.willystw.tabunganku.dto.response.AddCategoryResponse;
import com.willystw.tabunganku.dto.response.GetCategoryDetailResponse;
import com.willystw.tabunganku.dto.response.GetCategoryListResponse;
import com.willystw.tabunganku.mapper.CategoryMapper;
import com.willystw.tabunganku.model.Category;
import com.willystw.tabunganku.service.CategoryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoriesController implements ICategoriesController {
  private final CategoryService categoryService;

  public CategoriesController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  @PostMapping("/add")
  public AddCategoryResponse insertNewCategory(
      @RequestBody AddCategoryRequest request) {
    final var authToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    String subject = authToken.getToken().getClaimAsString(StandardClaimNames.SUB);
    UUID userId = UUID.fromString(subject);
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
  public GetCategoryListResponse getCategoryListByUserId() {
    final var authToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    String subject = authToken.getToken().getClaimAsString(StandardClaimNames.SUB);
    UUID userId = UUID.fromString(subject);

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
      @PathVariable Long categoryId) {
    final var authToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    String subject = authToken.getToken().getClaimAsString(StandardClaimNames.SUB);
    UUID userId = UUID.fromString(subject);

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
