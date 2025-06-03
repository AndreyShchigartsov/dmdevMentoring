package ru.sbercraft.service.integration.http.controller;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.integration.IntegrationTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.sbercraft.service.dto.event.EventCreateEditDto.Fields.*;
import static ru.sbercraft.service.dto.event.EventCreateEditDto.Fields.name;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class EventControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

//    @Test
//    void findAll() throws Exception {
//        mockMvc.perform(get("/event"))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(view().name("event/events"))
//                .andExpect(model().attributeExists("events"));
//    }
//
//    @Test
//    void create() throws Exception {
//        mockMvc.perform(post("/event/create")
//                .param(name, "hello")
//                .param(category, CategoryEvent.SPORT.name())
//        ).andExpectAll(
//                status().is3xxRedirection(),
//                redirectedUrlPattern("/event/{\\d+}")
//        );
//    }
}