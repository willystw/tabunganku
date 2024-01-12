package com.willystw.tabunganku.controller;

import com.willystw.tabunganku.dto.response.GetUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;

@Tag(name = "User", description = "User management APIs")
@Validated
public interface IUsersController {

    @Operation(
            summary = "Retrieve a user data",
            description = "Get a user from its Id. The response will have id"
                    + ", username, and email",
            tags = {"users", "get"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(
                            schema = @Schema(implementation = GetUserResponse.class),
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
    GetUserResponse getUserData(Long userId);
}
