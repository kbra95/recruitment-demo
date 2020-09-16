package com.apps.recruitment.controller;

import com.apps.recruitment.dao.InterviewEntity;
import com.apps.recruitment.service.impl.InterviewServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InterviewControllerTest {

    @MockBean
    private InterviewServiceImpl interviewServiceImpl;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetInterviewByIdFound() throws Exception{
        InterviewEntity mockEntity = new InterviewEntity();
        mockEntity.setId(1);
        mockEntity.setType("hr");
        when(interviewServiceImpl.getInterviewById(1)).thenReturn(mockEntity);
        mockMvc.perform(get("/interview/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id" ).value("1"))
                .andExpect(jsonPath("$.type").value("hr"));
    }

    @Test
    public void testGetInterviewByIdNotFound() throws Exception{
        when(interviewServiceImpl.getInterviewById(1)).thenReturn(null);
        mockMvc.perform(get("/interview/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateOrUpdateInterview() throws Exception{
        InterviewEntity postEntity = new InterviewEntity();
        postEntity.setId(1);
        postEntity.setType("hr");
        doReturn(postEntity).when(interviewServiceImpl).createOrUpdateInterviewEntity(postEntity);
        mockMvc.perform(post("/interview")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postEntity)))
                .andExpect(status().isCreated());
                //.andExpect(jsonPath("$.id" ).value("1"))
    }

    static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException("error when wrap json");
        }
    }
}