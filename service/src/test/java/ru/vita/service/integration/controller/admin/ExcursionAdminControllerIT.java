package ru.vita.service.integration.controller.admin;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.vita.service.integration.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class ExcursionAdminControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Andrey", authorities = {"ADMIN"})
    void checkThatCreatedExcursion() throws Exception {
        MvcResult result = mockMvc.perform(post("/admin/excursions/create")
                        .param("provinceId", "1")
                        .param("service", "Саб доски")
                        .param("price", "900")
                        .param("duration", "40")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/excursions"))
                .andReturn();

        assertThat(result.getFlashMap().keySet())
                .doesNotContain("errors");
    }

    @Test
    @WithMockUser(username = "Andrey", authorities = {"ADMIN"})
    void checkWhenTheyCreatedInvalidDto() throws Exception {
        mockMvc.perform(post("/admin/excursions/create")
                        .param("provinceId", "1")
                        .param("price", "900")
                        .param("duration", "40")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/excursions/create"))
                .andExpect(flash().attributeExists("errors"))
                .andExpect(flash().attribute("errors", hasSize(1)));
    }
}