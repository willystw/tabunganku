package com.willystw.tabunganku.repository;

import com.tabunganku.jooq.model.Tables;
import com.tabunganku.jooq.model.tables.Transactions;
import com.tabunganku.jooq.model.tables.records.TransactionsRecord;
import java.time.LocalDate;
import java.util.UUID;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import static com.tabunganku.jooq.model.Tables.TRANSACTIONS;

@Repository
public class TransactionsRepository {
  private final DSLContext context;

  public TransactionsRepository(DSLContext context) {
    this.context = context;
  }

  public Result<TransactionsRecord> getTransactionsByUserId(UUID userId,
                                                            LocalDate date) {
    return context.selectFrom(TRANSACTIONS).where(
        TRANSACTIONS.TRANSACTION_DATE.eq(date).and(
            TRANSACTIONS.USER_ID.eq(userId)
        )
    ).orderBy(TRANSACTIONS.ID.desc()).fetch();
  }

  public Long save(
      long transactionId,
      UUID userId,
      long categoryId,
      String note,
      Long amount,
      LocalDate date
  ) {
    var n = context.insertInto(
        TRANSACTIONS,
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
