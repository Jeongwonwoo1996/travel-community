package com.example.travelcommunity.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testuser@example.com", roles = {"USER"})
    void createPost_shouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/board/create")
                        .param("userId", "1")
                        .param("title", "새로운 게시글")
                        .param("content", "게시글 내용을 작성합니다.")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board"));
    }

    @Test
    @WithMockUser(username = "testuser@example.com", roles = {"USER"})
    void getPost_shouldReturnPost() throws Exception {
        mockMvc.perform(get("/board/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/view"))
                .andExpect(model().attributeExists("post"));
    }

    @Test
    @WithMockUser(username = "testuser@example.com", roles = {"USER"})
    void deletePost_shouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/board/delete/1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board"));
    }


}
