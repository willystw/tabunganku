package com.willystw.tabunganku.repository;

import com.tabunganku.jooq.model.tables.Categories;
import com.tabunganku.jooq.model.tables.records.CategoriesRecord;
import com.willystw.tabunganku.model.TransactionType;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.tabunganku.jooq.model.Tables.CATEGORIES;

@Repository
public class CategoriesRepository {
  private final DSLContext context;

  public CategoriesRepository(DSLContext context) {
    this.context = context;
  }

  public CategoriesRecord getById(long categoryId, UUID userId) {
    return context.selectFrom(CATEGORIES).where(
        CATEGORIES.ID.eq(categoryId)
            .and(CATEGORIES.USER_ID.eq(userId))
    ).fetchOne();
  }

  public Result<CategoriesRecord> getCategoriesByUserId(UUID userId) {
    return context.selectFrom(CATEGORIES).where(
        CATEGORIES.USER_ID.eq(userId).and(CATEGORIES.ACTIVE.eq(true))
    ).orderBy(CATEGORIES.ID.desc()).fetch();
  }

  public Long save(
      UUID userId,
      String name,
      TransactionType type) {
    return context.insertInto(
        CATEGORIES,
            Categories.CATEGORIES.USER_ID,
            Categories.CATEGORIES.NAME,
            Categories.CATEGORIES.TYPE
        )
        .values(userId, name, type.toString())
        .returningResult(Categories.CATEGORIES.ID)
        .fetchOne()
        .value1();
  }
}
