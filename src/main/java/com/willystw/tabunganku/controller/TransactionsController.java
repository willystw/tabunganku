package com.willystw.tabunganku.controller;

import com.willystw.tabunganku.dto.TransactionDto;
import com.willystw.tabunganku.dto.TransactionSummaryDto;
import com.willystw.tabunganku.dto.request.AddTransactionRequest;
import com.willystw.tabunganku.dto.response.AddTransactionResponse;
import com.willystw.tabunganku.dto.response.GetTransactionListResponse;
import com.willystw.tabunganku.dto.response.GetTransactionSummaryResponse;
import com.willystw.tabunganku.mapper.TransactionMapper;
import com.willystw.tabunganku.mapper.TransactionSummaryMapper;
import com.willystw.tabunganku.model.Transaction;
import com.willystw.tabunganku.model.TransactionSummary;
import com.willystw.tabunganku.model.TransactionType;
import com.willystw.tabunganku.service.TransactionService;
import com.willystw.tabunganku.service.TransactionSummaryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users/{userId}/transactions")
public class TransactionsController implements ITransactionsController {

  private final TransactionService transactionService;
  private final TransactionSummaryService transactionSummaryService;

  public TransactionsController(TransactionService transactionService,
      TransactionSummaryService transactionSummaryService) {
    this.transactionService = transactionService;
    this.transactionSummaryService = transactionSummaryService;
  }

  @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public AddTransactionResponse addTransactions(
          @PathVariable Long userId,
          @RequestBody AddTransactionRequest request
  ) {

    AddTransactionResponse response = new AddTransactionResponse();
    Long transactionId = transactionService.addNewTransaction(
        request.getAmount(),
        request.getDate(),
        request.getNote(),
        request.getCategoryId(),
        userId
    );

    if (transactionId != null) {
      response.setTransactionId(transactionId);
    } else {
      response.setMessage("Fail to create transaction");

    }

    return response;
  }

  @GetMapping("/get/{transactionDate}")
  public GetTransactionListResponse getTransactions(
      @PathVariable long userId,
      @PathVariable LocalDate transactionDate) {
    List<Transaction> transactions = transactionService.getTransactions(userId, transactionDate);
    GetTransactionListResponse response = new GetTransactionListResponse();

    List<TransactionDto> transactionDtoList = new ArrayList<>();
    for (Transaction transaction: transactions) {
      TransactionDto dto = TransactionMapper.INSTANCE.transactionToTransactionDto(transaction);
      transactionDtoList.add(dto);
    }

    response.setTransactions(transactionDtoList);

    return response;
  }

  @GetMapping("/summary/{transactionDate}")
  public GetTransactionSummaryResponse getSummary(
      @PathVariable long userId,
      @PathVariable LocalDate transactionDate) {
    List<TransactionSummary> transactionSummaryList = transactionSummaryService.getTransactionSummary(
        userId, transactionDate);

    long totalEarning = 0;
    long totalExpense = 0;

    Map<String, Long> categoryTotalMap = new HashMap<>();
    Map<String, TransactionType> categoryTypeMap = new HashMap<>();

    List<TransactionSummaryDto> transactionSummaryDtos = new ArrayList<>();
    for (TransactionSummary ts: transactionSummaryList) {
      long amount = ts.getAmount();
      if (ts.getType() == TransactionType.EARNING) {
        totalEarning += amount;
      } else {
        totalExpense += amount;
      }

      String catName = ts.getCategoryName();
      if (categoryTotalMap.containsKey(catName)) {
        Long existingTotal = categoryTotalMap.get(catName);
        categoryTotalMap.put(catName, existingTotal + amount);
      } else {
        categoryTotalMap.put(catName, amount);
      }
      categoryTypeMap.put(catName, ts.getType());
      transactionSummaryDtos.add(
          TransactionSummaryMapper.INSTANCE.transactionSummaryToTransactionSummaryDto(ts));
    }

    List<String> categoryNameList = categoryTotalMap.keySet().stream().toList();
    List<Long> earningAmountList = new ArrayList<>();
    List<Long> expenseAmountList = new ArrayList<>();
    for (String cat: categoryNameList) {
      if (categoryTypeMap.get(cat) == TransactionType.EARNING) {
        earningAmountList.add(categoryTotalMap.get(cat));
        expenseAmountList.add(0L);
      } else {
        earningAmountList.add(0L);
        expenseAmountList.add(categoryTotalMap.get(cat));
      }
    }

    GetTransactionSummaryResponse response = new GetTransactionSummaryResponse();
    response.setTotalEarning(totalEarning);
    response.setTotalExpense(totalExpense);
    response.setAvailableCategories(categoryNameList);
    response.setEarningAmounts(earningAmountList);
    response.setExpenseAmounts(expenseAmountList);
    response.setTransactionSummaryList(transactionSummaryDtos);

    return response;
  }

}
