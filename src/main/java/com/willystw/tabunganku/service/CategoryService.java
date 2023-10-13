package com.willystw.tabunganku.service;

import com.tabunganku.jooq.model.Tables;
import com.tabunganku.jooq.model.tables.records.CategoriesRecord;
import com.willystw.tabunganku.model.Category;
import com.willystw.tabunganku.model.TransactionType;
import com.willystw.tabunganku.model.User;
import com.willystw.tabunganku.repository.CategoriesRepository;
import org.jooq.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {
  private final UserService userService;
  private final CategoriesRepository categoryRepository;

  public CategoryService(UserService userService,
      CategoriesRepository categoriesRepository) {
    this.userService = userService;
    this.categoryRepository = categoriesRepository;
  }

  public Category getById(long categoryId, long userId) {
    CategoriesRecord record = categoryRepository.getById(categoryId, userId);

    if (record != null) {
      Category category = new Category();
      category.setCategoryId(record.getId());
      category.setUserId(record.getUserId());
      category.setActive(record.getActive());
      category.setName(record.getName());
      category.setType(TransactionType.valueOf(record.getType()));

      return category;
    }

    return null;
  }

  public List<Category> getCategoryList(long userId) {
    Result<CategoriesRecord> categoryRecords = categoryRepository.getCategoriesByUserId(userId);

    List<Category> categories = new ArrayList<>();

    for (CategoriesRecord r: categoryRecords) {
      Category c = new Category();
      c.setCategoryId(r.getValue(Tables.CATEGORIES.ID));
      c.setName(r.getValue(Tables.CATEGORIES.NAME));
      c.setUserId(r.getValue(Tables.CATEGORIES.USER_ID));
      c.setActive(r.getValue(Tables.CATEGORIES.ACTIVE));
      c.setType(r.getValue(Tables.CATEGORIES.TYPE, TransactionType.class));

      categories.add(c);
    }

    return categories;
  }

  public Long addNewCategory(Long userId,
      String name,
      TransactionType type) {
    User user = userService.getById(userId);
    if (user != null) {
      return categoryRepository.save(
          userId, name, type
      );
    }

    return null;
  }

}
