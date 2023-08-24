package com.willystw.tabunganku.model;

public class TransactionSummary extends Transaction {
  private String categoryName;
  private TransactionType type;

  public TransactionSummary() {
  }

  public TransactionSummary(Transaction t) {
    setTransactionId(t.getTransactionId());
    setUserId(t.getUserId());
    setCategoryId(t.getCategoryId());
    setAmount(t.getAmount());
    setDate(t.getDate());
    setNote(t.getNote());
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }
}
