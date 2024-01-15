package com.willystw.tabunganku.controller;

import com.willystw.tabunganku.dto.request.AddTransactionRequest;
import com.willystw.tabunganku.dto.response.AddTransactionResponse;
import com.willystw.tabunganku.dto.response.GetTransactionListResponse;
import com.willystw.tabunganku.dto.response.GetTransactionSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Tag(name = "Transaction", description = "Transaction management APIs")
@Validated
public interface ITransactionsController {

  @Operation(
      summary = "Save a new transaction",
      description = "Add a new transaction. The request must provide amount, transaction_date, user_id"
          + ", category_id, and note",
      tags = {"transactions", "save"}
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {@Content(
              schema = @Schema(implementation = AddTransactionResponse.class),
              mediaType = "application/json")
          }
      ),
      @ApiResponse(
          responseCode = "400",
          content = {@Content(
              schema = @Schema(ref = "#/components/schemas/ErrorBody"),
              mediaType = "application/json")
          }
      )
  })
  AddTransactionResponse addTransactions(@Valid AddTransactionRequest request);

  @Operation(
      summary = "Retrieve transaction list",
      description = "Get a list of transaction by specifying user Id and date. The response is a list of Transaction object",
      tags = {"transactions", "get"}
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {@Content(
              schema = @Schema(implementation = GetTransactionListResponse.class),
              mediaType = "application/json")
          }
      ),
      @ApiResponse(
          responseCode = "400",
          content = {@Content(
              schema = @Schema(ref = "#/components/schemas/ErrorBody"),
              mediaType = "application/json")
          }
      )
  })
  GetTransactionListResponse getTransactions(LocalDate transactionDate);


  @Operation(
      summary = "Retrieve transaction summary list",
      description = "Get a list of transaction and more by specifying user Id and date.",
      tags = {"transactions", "summary"}
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {@Content(
              schema = @Schema(implementation = GetTransactionSummaryResponse.class),
              mediaType = "application/json")
          }
      ),
      @ApiResponse(
          responseCode = "400",
          content = {@Content(
              schema = @Schema(ref = "#/components/schemas/ErrorBody"),
              mediaType = "application/json")
          }
      )
  })
  GetTransactionSummaryResponse getSummary(LocalDate transactionDate);
}
