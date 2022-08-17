package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorControllerIT {

  @Autowired
  private MockMvc mockMvc;

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
  @WithMockUser(
          username = "stoyan",
          password = "stoyan",
          roles = "USER"
  )
  public void getError() throws Exception {
    mockMvc.perform(get("/admin"))
            .andExpect(status().is4xxClientError());
  }


  @Test
  @WithMockUser(
          username = "zdravko",
          password = "admin",
          roles = "ADMIN"
  )
  public void getErrorForNotExistingUser() throws Exception {
    mockMvc.perform(get("/user/150"))
            .andExpect(status().is4xxClientError());
  }


}
