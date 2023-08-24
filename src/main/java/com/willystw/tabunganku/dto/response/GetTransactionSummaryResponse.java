package com.willystw.tabunganku.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willystw.tabunganku.dto.TransactionSummaryDto;
import java.util.List;

public class GetTransactionSummaryResponse {

  @JsonProperty("available_categories")
  private List<String> availableCategories;

  @JsonProperty("earnings")
  private List<Long> earningAmounts;

  @JsonProperty("expenses")
  private List<Long> expenseAmounts;

  @JsonProperty("total_earning")
  private long totalEarning;

  @JsonProperty("total_expense")
  private long totalExpense;

  @JsonProperty("transactions")
  private List<TransactionSummaryDto> transactionSummaryList;

  public GetTransactionSummaryResponse() {
  }

  public List<String> getAvailableCategories() {
    return availableCategories;
  }

  public void setAvailableCategories(
      List<String> availableCategories) {
    this.availableCategories = availableCategories;
  }

  public List<Long> getEarningAmounts() {
    return earningAmounts;
  }

  public void setEarningAmounts(List<Long> earningAmounts) {
    this.earningAmounts = earningAmounts;
  }

  public List<Long> getExpenseAmounts() {
    return expenseAmounts;
  }

  public void setExpenseAmounts(List<Long> expenseAmounts) {
    this.expenseAmounts = expenseAmounts;
  }

  public List<TransactionSummaryDto> getTransactionSummaryList() {
    return transactionSummaryList;
  }

  public void setTransactionSummaryList(
      List<TransactionSummaryDto> transactionSummaryList) {
    this.transactionSummaryList = transactionSummaryList;
  }

  public long getTotalEarning() {
    return totalEarning;
  }

  public void setTotalEarning(long totalEarning) {
    this.totalEarning = totalEarning;
  }

  public long getTotalExpense() {
    return totalExpense;
  }

  public void setTotalExpense(long totalExpense) {
    this.totalExpense = totalExpense;
  }
}
