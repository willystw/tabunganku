package com.willystw.tabunganku.mapper;

import com.willystw.tabunganku.dto.CategoryDto;
import com.willystw.tabunganku.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  @Mapping(target = "id", source = "categoryId")
  CategoryDto categoryToCategoryDto(Category category);
}
