package com.apps.recruitment.controller;

import com.apps.recruitment.dao.CandidateEntity;
import com.apps.recruitment.service.impl.CandidateServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CandidateControllerTest {

    @MockBean
    private CandidateServiceImpl candidateServiceImpl;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCandidateByIdFound() throws Exception{
        CandidateEntity mockEntity = new CandidateEntity();
        mockEntity.setId(1);
        mockEntity.setDepartment("Wireless");
        mockEntity.setStatus("junior");
        when(candidateServiceImpl.getCandidateById(1)).thenReturn(mockEntity);
        mockMvc.perform(get("/candidate/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id" ).value("1"))
                .andExpect(jsonPath("$.department").value("Wireless"));
    }

    @Test
    public void testGetCandidateByIdNotFound() throws Exception{
        when(candidateServiceImpl.getCandidateById(1)).thenReturn(null);
        mockMvc.perform(get("/candidate/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateOrUpdateCandidate() throws Exception{
        CandidateEntity postEntity = new CandidateEntity();
        postEntity.setId(1);
        postEntity.setDepartment("Wireless");
        postEntity.setStatus("junior");
        postEntity.setStatusCode(CandidateServiceImpl.PASS);
        postEntity.setType("hr");

        doReturn(postEntity).when(candidateServiceImpl).createOrUpdateCandidateEntity(postEntity);
        mockMvc.perform(post("/candidate")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(postEntity)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCandidateDeleteSuccess() throws Exception{
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(1);
        candidateEntity.setType("senior");
        candidateEntity.setName("xxx yyy");

        doReturn(candidateEntity).when(candidateServiceImpl).getCandidateById(1);
        doReturn(true).when(candidateServiceImpl).deleteCandidateById(1);
        mockMvc.perform(delete("/candidate/{id}",1))
                .andExpect(status().isOk());
    }

    static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException("error when wrap json");
        }
    }
}
