package bg.softuni.HappyCats.web;


import bg.softuni.HappyCats.config.HappyPetsSecurityConfiguration;
import bg.softuni.HappyCats.config.WebConfig;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import({HappyPetsSecurityConfiguration.class})
public class ProfileControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TestDataUtils testDataUtils;

    private User testUser, testAdmin;


    @BeforeEach
    void setUp() throws Exception {
        testUser = testDataUtils.createTestUser("user@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin@example.com");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
        SecurityContextHolder.clearContext();
    }

    @Test
    public void getProfilePage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/profile",
                String.class)).contains("Our Services");
    }



    @Test
    public void openProfileWithoutUserAccount() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection()).
        andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    public void openChangeUserName() throws Exception {
        mockMvc.perform(post("/changeUsername").param("username", "ivancheto"))
                .andExpect(status().is4xxClientError());
    }




}