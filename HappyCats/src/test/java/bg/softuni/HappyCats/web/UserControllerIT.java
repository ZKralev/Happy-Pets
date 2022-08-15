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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

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
          username = "zdravko",
          password = "admin"
  )
  void testAddComment() throws Exception {
    mockMvc.perform(post("/comment").
            param("email", "zdravko@example.com").
            param("message", "Hello!").
            with(csrf())
        ).
        andExpect(status().is3xxRedirection()).
        andExpect(redirectedUrl("/index"));
  }
  @Test
  @WithMockUser(
          username = "zdravko",
          password = "admin"
  )
  void testAddBooking() throws Exception {
    mockMvc.perform(post("/booking").
                    param("name", "ivancho").
                    param("email", "ivan@example.com").
                    param("date-time", "12-27-2022 15:41:10").
                    param("service", "1").
                    with(csrf())
            ).
            andExpect(status().is3xxRedirection()).
            andExpect(redirectedUrl("/booking"));
  }

  @Test
  @WithMockUser(
          username = "zdravko",
          password = "admin"
  )
  void testAddPet() throws Exception {
    mockMvc.perform(post("/pets").
                    param("name", "ivancho").
                    param("age", "2").
                    param("kind", "Cat").
                    param("breed", "British").
                    param("owner", "zdravko").
                    with(csrf())
            ).
            andExpect(status().is3xxRedirection()).
            andExpect(redirectedUrl("/pets"));
  }



}
