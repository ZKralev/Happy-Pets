package bg.softuni.HappyCats.web;


import bg.softuni.HappyCats.model.entity.Booking;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AdminControllerIT {

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
    void setUp() {
        testUser = testDataUtils.createTestUser("user@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin@example.com");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    public void getAdminPage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/users",
                String.class)).contains("Our Services");
    }

    @Test
    @WithMockUser(
            username = "zdravko",
            password = "4b148b365433c559fdc07a0742712e88b61d5e23a52bb10206c308908e2e67836ecb3ff5714006ea",
            roles = "ADMIN"
    )
    public void openAdminPage() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(
            username = "zdravko",
            password = "4b148b365433c559fdc07a0742712e88b61d5e23a52bb10206c308908e2e67836ecb3ff5714006ea",
            roles = "USER"
    )
    public void openAdminWithoutAdminRight() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is4xxClientError());
    }
    @Test
    @WithMockUser(
            username = "zdravko",
            password = "4b148b365433c559fdc07a0742712e88b61d5e23a52bb10206c308908e2e67836ecb3ff5714006ea",
            roles = "ADMIN"
    )
    public void openUserWithAdmin() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(
            username = "admin@example.com"
    )
    public void openChangeRoleToAdminOfUserWithAdmin() throws Exception {
        mockMvc.perform(get("/makeAdmin/2"))
                .andExpect(status().is4xxClientError());
    }




}