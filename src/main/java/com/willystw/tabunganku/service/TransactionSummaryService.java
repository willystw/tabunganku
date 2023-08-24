package com.willystw.tabunganku.service;

import com.willystw.tabunganku.model.Category;
import com.willystw.tabunganku.model.Transaction;
import com.willystw.tabunganku.model.TransactionSummary;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionSummaryService {

  private final TransactionService transactionService;

  private final CategoryService categoryService;

  public TransactionSummaryService(TransactionService transactionService,
      CategoryService categoryService) {
    this.transactionService = transactionService;
    this.categoryService = categoryService;
  }

  public List<TransactionSummary> getTransactionSummary(long userId, LocalDate localDate) {
    List<Transaction> transactions = transactionService.getTransactions(userId, localDate);
    List<Category> categories = categoryService.getCategoryList(userId);

    Map<Long, Category> categoryMap = categories
        .stream()
        .collect(Collectors.toMap(Category::getCategoryId, Function.identity()));

    List<TransactionSummary> result = new ArrayList<>();
    for (Transaction t: transactions) {
      TransactionSummary s = new TransactionSummary(t);

      Category c = categoryMap.get(s.getCategoryId());
      s.setCategoryName(c.getName());
      s.setType(c.getType());

      result.add(s);
    }

    return result;

  }
}
