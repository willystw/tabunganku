package com.willystw.tabunganku.mapper;

import com.willystw.tabunganku.dto.TransactionSummaryDto;
import com.willystw.tabunganku.model.TransactionSummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionSummaryMapper {
  TransactionSummaryMapper INSTANCE = Mappers.getMapper(TransactionSummaryMapper.class);

  @Mapping(target = "id", source = "transactionId")
  @Mapping(target = "categoryType", source = "type")
  TransactionSummaryDto transactionSummaryToTransactionSummaryDto(TransactionSummary category);

}
