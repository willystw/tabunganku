package com.willystw.tabunganku.repository;

import com.tabunganku.jooq.model.Tables;
import com.tabunganku.jooq.model.tables.Categories;
import com.tabunganku.jooq.model.tables.records.CategoriesRecord;
import com.willystw.tabunganku.model.TransactionType;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriesRepository {
  private final DSLContext context;

  public CategoriesRepository(DSLContext context) {
    this.context = context;
  }

  public CategoriesRecord getById(long categoryId, long userId) {
    return context.selectFrom(Tables.CATEGORIES).where(
        Tables.CATEGORIES.ID.eq(categoryId)
            .and(Tables.CATEGORIES.USER_ID.eq(userId))
    ).fetchOne();
  }

  public Result<CategoriesRecord> getCategoriesByUserId(long userId) {
    return context.selectFrom(Tables.CATEGORIES).where(
        Tables.CATEGORIES.USER_ID.eq(userId).and(Tables.CATEGORIES.ACTIVE.eq(true))
    ).fetch();
  }

  public Long save(
      long userId,
      String name,
      TransactionType type) {
    return context.insertInto(
        Tables.CATEGORIES,
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
