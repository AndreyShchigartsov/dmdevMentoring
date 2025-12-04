package ru.vita.service.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.vita.service.integration.IntegrationTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class EventControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Andrey", authorities = {"USER", "WORKER", "CAMPER"})
    void findAll() throws Exception {
        mockMvc.perform(get("/user/events"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/event/events"))
                .andExpect(model().attributeExists("events"));
    }
}