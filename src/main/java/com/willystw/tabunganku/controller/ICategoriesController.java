package com.willystw.tabunganku.controller;

import com.willystw.tabunganku.dto.request.AddCategoryRequest;
import com.willystw.tabunganku.dto.response.AddCategoryResponse;
import com.willystw.tabunganku.dto.response.GetCategoryDetailResponse;
import com.willystw.tabunganku.dto.response.GetCategoryListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Tag(name = "Category", description = "Category management APIs")
@Validated
public interface ICategoriesController {

  @Operation(
      summary = "Add a new category",
      description = "Add a new category. The request must provide user_id, category_name"
          + ", transaction_type",
      tags = {"categories", "save"}
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {@Content(
              schema = @Schema(implementation = AddCategoryResponse.class),
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
  AddCategoryResponse insertNewCategory(long userId, @Valid AddCategoryRequest request);

  @Operation(
      summary = "Retrieve categories by User Id",
      description = "Get a list of category by specifying its user ID. The response is a list of Category object"
          + " with id, userId, name, icon, active, and type.",
      tags = {"categories", "get"}
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {@Content(
              schema = @Schema(implementation = GetCategoryListResponse.class),
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
  GetCategoryListResponse getCategoryListByUserId(Long userId);

  @Operation(
      summary = "Retrieve a category by Id",
      description = "Get a category by specifying its Id and user Id. The response is a Category object"
          + " with id, name, icon, is_active, and type. Return a message if a category is not found",
      tags = {"categories", "get"}
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {@Content(
              schema = @Schema(implementation = GetCategoryDetailResponse.class),
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
  GetCategoryDetailResponse getCategoryDetail(Long userId, Long categoryId);

}
