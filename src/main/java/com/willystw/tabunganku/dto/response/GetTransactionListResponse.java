package com.willystw.tabunganku.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willystw.tabunganku.dto.TransactionDto;
import java.util.List;

public class GetTransactionListResponse {

  @JsonProperty("transaction_list")
  private List<TransactionDto> transactions;

  public GetTransactionListResponse() {
  }

  public List<TransactionDto> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<TransactionDto> transactions) {
    this.transactions = transactions;
  }
}
