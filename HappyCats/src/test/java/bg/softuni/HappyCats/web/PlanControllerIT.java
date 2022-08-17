package bg.softuni.HappyCats.web;


import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.enums.UserRoleEnum;
import bg.softuni.HappyCats.repository.UserRepository;
import bg.softuni.HappyCats.service.AuthService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PlanControllerIT {


    @Autowired
    private MockMvc mockMvc;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void getPlanPage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/price",
                String.class)).contains("Pricing Plan");
    }

    @Test
    @WithMockUser(
            username = "zdravko",
            password = "admin"
    )
    void testAddPlan() throws Exception {
        mockMvc.perform(post("/addPlanStandard").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/index"));
    }
}