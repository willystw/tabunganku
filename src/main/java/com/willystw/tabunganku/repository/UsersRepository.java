package com.willystw.tabunganku.repository;

import com.tabunganku.jooq.model.Tables;
import com.tabunganku.jooq.model.tables.Users;
import com.tabunganku.jooq.model.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {

  private final DSLContext context;

  public UsersRepository(DSLContext context) {
    this.context = context;
  }

  public UsersRecord getById(long userId) {
    return context.selectFrom(Tables.USERS).where(
        Tables.USERS.ID.eq(userId)
    ).fetchOne();
  }

  public boolean isUserAlreadyExists(String username, String email) {
    UsersRecord record = context.selectFrom(Tables.USERS)
        .where(Tables.USERS.EMAIL.eq(email)
            .or(Tables.USERS.USERNAME.eq(username)))
        .fetchAny();
    return record != null;
  }

  public Long save(long id, String username, String email) {
    return context.insertInto(
        Tables.USERS, Users.USERS.ID, Users.USERS.USERNAME, Users.USERS.EMAIL)
        .values(id, username, email)
        .returningResult(Users.USERS.ID)
        .fetchOne()
        .value1();
  }
}
