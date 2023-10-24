package com.willystw.tabunganku.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willystw.tabunganku.dto.request.AddCategoryRequest;
import com.willystw.tabunganku.model.TransactionType;
import com.willystw.tabunganku.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriesController.class)
public class CategoriesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testInsertNewCategories_nullCategoryName_failed() throws Exception {
        AddCategoryRequest request = new AddCategoryRequest();
        request.setType(TransactionType.EARNING);

        mockMvc.perform(post("/users/123/categories/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInsertNewCategories_emptyStringCategoryName_failed() throws Exception {
        AddCategoryRequest request = new AddCategoryRequest();
        request.setName("");
        request.setType(TransactionType.EARNING);

        mockMvc.perform(post("/users/123/categories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInsertNewCategories_nullCategoryType_failed() throws Exception {
        AddCategoryRequest request = new AddCategoryRequest();
        request.setName("a");

        mockMvc.perform(post("/users/123/categories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInsertNewCategories_validInput_success() throws Exception {
        AddCategoryRequest request = new AddCategoryRequest();
        request.setName("a");
        request.setType(TransactionType.EARNING);

        when(categoryService.addNewCategory(
                anyLong(),
                anyString(),
                any())).thenReturn(100L);

        mockMvc.perform(post("/users/123/categories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


}
