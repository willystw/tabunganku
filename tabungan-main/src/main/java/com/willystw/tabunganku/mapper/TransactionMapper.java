package com.willystw.tabunganku.mapper;

import com.willystw.tabunganku.dto.TransactionDto;
import com.willystw.tabunganku.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

  @Mapping(target = "id", source = "transactionId")
  TransactionDto transactionToTransactionDto(Transaction category);
}
