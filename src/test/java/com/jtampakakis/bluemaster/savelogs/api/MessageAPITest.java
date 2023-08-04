package com.jtampakakis.bluemaster.savelogs.api;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import com.jtampakakis.bluemaster.savelogs.entities.MessageEntity;
import com.jtampakakis.bluemaster.savelogs.services.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MessageAPI.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class MessageAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;
//https://www.baeldung.com/spring-boot-testing
    @Test
    void shouldGetAllMessages() throws Exception {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(1L);
        messageEntity.setUuid("test-uuid");
        // Set other fields if necessary

        List<MessageEntity> messages = Collections.singletonList(messageEntity);
        given(messageService.getAllMessages()).willReturn(messages);

        mockMvc.perform(get("/messages")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(messageEntity.getId()));
    }

    @Test
    void shouldGetMessageById() throws Exception {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(1L);
        messageEntity.setUuid("test-uuid");

        given(messageService.getMessageById(1L)).willReturn(messageEntity);

        mockMvc.perform(get("/messages/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(messageEntity.getId()))
                .andExpect(jsonPath("$.uuid").value(messageEntity.getUuid()));

    }
}