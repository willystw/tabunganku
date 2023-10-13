package com.willystw.tabunganku.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willystw.tabunganku.dto.request.AddCategoryRequest;
import com.willystw.tabunganku.model.TransactionType;
import com.willystw.tabunganku.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CategoriesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testInsertNewCategories_invalidName_failed() throws Exception {
        AddCategoryRequest request = new AddCategoryRequest();
        request.setType(TransactionType.EARNING);

        //only to test if @Mock returning error or not, delete after adding more tests
        lenient().when(categoryService.addNewCategory(
                anyLong(),
                anyString(),
                any())).thenReturn(100L);

        mockMvc.perform(post("/users/123/categories/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
