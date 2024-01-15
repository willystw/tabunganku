package com.willystw.tabunganku.repository;

import com.tabunganku.jooq.model.Tables;
import com.tabunganku.jooq.model.tables.Users;
import com.tabunganku.jooq.model.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.tabunganku.jooq.model.Tables.USERS;

@Repository
public class UsersRepository {

  private final DSLContext context;

  public UsersRepository(DSLContext context) {
    this.context = context;
  }

  public UsersRecord getById(UUID userId) {
    return context.selectFrom(USERS).where(
        USERS.USER_ID.eq(userId)
    ).fetchOne();
  }

  public boolean isUserAlreadyExists(String username, String email) {
    UsersRecord record = context.selectFrom(USERS)
        .where(USERS.EMAIL.eq(email)
            .or(USERS.USERNAME.eq(username)))
        .fetchAny();
    return record != null;
  }

  public UUID save(UUID id, String username, String email) {
    return context.insertInto(
        USERS, Users.USERS.USER_ID, Users.USERS.USERNAME, Users.USERS.EMAIL)
        .values(id, username, email)
        .returningResult(Users.USERS.USER_ID)
        .fetchOne()
        .value1();
  }
}
