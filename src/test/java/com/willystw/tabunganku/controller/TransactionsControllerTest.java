package com.willystw.tabunganku.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willystw.tabunganku.dto.request.AddTransactionRequest;
import com.willystw.tabunganku.service.TransactionService;
import com.willystw.tabunganku.service.TransactionSummaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @Mock
    private TransactionSummaryService transactionSummaryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTransaction_invalidAmount_failed() throws Exception {
        AddTransactionRequest request = new AddTransactionRequest();
        request.setAmount(0L);
        request.setDate(LocalDate.now());
        request.setCategoryId(20000L);

        //only to test if @Mock returning error or not, delete after adding more tests
        lenient().when(transactionService.addNewTransaction(anyLong(),
                any(LocalDate.class),
                anyString(),
                anyLong(),
                anyLong())).thenReturn(
                10L
        );

        mockMvc.perform(post("/users/123/transactions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }
}
