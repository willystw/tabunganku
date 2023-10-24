package com.willystw.tabunganku.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willystw.tabunganku.dto.request.AddTransactionRequest;
import com.willystw.tabunganku.service.TransactionService;
import com.willystw.tabunganku.service.TransactionSummaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private TransactionSummaryService transactionSummaryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTransaction_validInput_success() throws Exception {
        AddTransactionRequest request = new AddTransactionRequest();
        request.setAmount(1L);
        request.setDate(LocalDate.now());
        request.setCategoryId(20000L);

        when(transactionService.addNewTransaction(anyLong(),
                any(LocalDate.class),
                anyString(),
                anyLong(),
                anyLong())).thenReturn(
                10L
        );

        mockMvc.perform(post("/users/123/transactions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void createTransaction_invalidAmount_failed() throws Exception {
        AddTransactionRequest request = new AddTransactionRequest();
        request.setAmount(0L);
        request.setDate(LocalDate.now());
        request.setCategoryId(20000L);

        mockMvc.perform(post("/users/123/transactions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTransaction_nullCategory_failed() throws Exception {
        AddTransactionRequest request = new AddTransactionRequest();
        request.setAmount(1L);
        request.setDate(null);
        request.setCategoryId(20000L);

        mockMvc.perform(post("/users/123/transactions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTransaction_nullDate_failed() throws Exception {
        AddTransactionRequest request = new AddTransactionRequest();
        request.setAmount(1L);
        request.setDate(LocalDate.now());
        request.setCategoryId(null);

        mockMvc.perform(post("/users/123/transactions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }
}
