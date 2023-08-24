package com.willystw.tabunganku.repository;

import com.tabunganku.jooq.model.Tables;
import com.tabunganku.jooq.model.tables.Transactions;
import com.tabunganku.jooq.model.tables.records.TransactionsRecord;
import java.time.LocalDate;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionsRepository {
  private final DSLContext context;

  public TransactionsRepository(DSLContext context) {
    this.context = context;
  }

  public Result<TransactionsRecord> getTransactionsByUserId(long userId,
      LocalDate date) {
    return context.selectFrom(Tables.TRANSACTIONS).where(
        Tables.TRANSACTIONS.TRANSACTION_DATE.eq(date).and(
            Tables.TRANSACTIONS.USER_ID.eq(userId)
        )
    ).fetch();
  }

  public Long save(
      long transactionId,
      long userId,
      long categoryId,
      String note,
      Long amount,
      LocalDate date
  ) {
    var n = context.insertInto(
        Tables.TRANSACTIONS,
        Transactions.TRANSACTIONS.ID,
        Transactions.TRANSACTIONS.USER_ID,
        Transactions.TRANSACTIONS.CATEGORY_ID,
        Transactions.TRANSACTIONS.NOTE,
        Transactions.TRANSACTIONS.AMOUNT,
        Transactions.TRANSACTIONS.TRANSACTION_DATE
    )
        .values(transactionId, userId, categoryId, note, amount, date)
        .returningResult(Transactions.TRANSACTIONS.ID)
        .fetchOne();

    if (n != null) {
      return n.value1();
    }
    return null;
  }
}
