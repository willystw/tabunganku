package com.willystw.tabunganku.service;

import com.tabunganku.jooq.model.tables.records.TransactionsRecord;
import com.willystw.tabunganku.model.Transaction;
import com.willystw.tabunganku.repository.TransactionsRepository;
import org.jooq.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionService {

  private final TransactionsRepository transactionsRepository;
  private final SecureLongRandomGenerator longIdGenerator;

  public TransactionService(TransactionsRepository transactionsRepository,
      SecureLongRandomGenerator longIdGenerator) {
    this.transactionsRepository = transactionsRepository;
    this.longIdGenerator = longIdGenerator;
  }

  public Long addNewTransaction(long amount,
      LocalDate date,
      String note,
      long categoryId,
      long userId) {
    return transactionsRepository.save(
        longIdGenerator.nextId(),
        userId,
        categoryId,
        note,
        amount,
        date
    );
  }

  public List<Transaction> getTransactions(long userId, LocalDate localDate) {
    Result<TransactionsRecord> transactionsRecords = transactionsRepository
        .getTransactionsByUserId(userId, localDate);

    List<Transaction> result = new ArrayList<>();

    for (TransactionsRecord record: transactionsRecords) {
      Transaction transaction = new Transaction();
      transaction.setTransactionId(record.getId());
      transaction.setUserId(record.getUserId());
      transaction.setCategoryId(record.getCategoryId());
      transaction.setAmount(record.getAmount());
      transaction.setDate(record.getTransactionDate());
      transaction.setNote(record.getNote());

      result.add(transaction);
    }
    return result;
  }
}
