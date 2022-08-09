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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

  private Booking testOffer, testAdminOffer;

  @BeforeEach
  void setUp() {
    testUser = testDataUtils.createTestUser("user@example.com");
    testAdmin = testDataUtils.createTestAdmin("admin@example.com");
    var testBooking =
        testDataUtils.createTestBooking(testUser);
    var testComment =
            testDataUtils.createTestComment(testAdmin);

  }

  @AfterEach
  void tearDown() {
    testDataUtils.cleanUpDatabase();
  }

  @Test
  void testDeleteByAnonymousUser_Forbidden() throws Exception {
    mockMvc.perform(delete("/user/{id}", testUser.getId()).
                    with(csrf())
            ).
            andExpect(status().is3xxRedirection());
    //TODO: check redirection url to login w/o schema
  }
  @Test
  @WithMockUser(
      username = "adminche",
      roles = {"ADMIN", "USER"}
  )
  void testDeleteByAdmin() throws Exception {
    mockMvc.perform(delete("/user/{id}", testUser.getId()).
            with(csrf())
        ).
        andExpect(status().is3xxRedirection()).
        andExpect(view().name("redirect:/admin"));
  }

  @WithMockUser(
      username = "userche",
      roles = "USER"
  )
  @Test
  void testDeleteByOwner() throws Exception {
    mockMvc.perform(delete("/user/{id}", testUser.getId()).
            with(csrf())
        ).
        andExpect(status().is3xxRedirection()).
        andExpect(view().name("redirect:/admin"));
  }

  @WithMockUser(
      username = "userche",
      roles = "USER"
  )
  @Test
  public void testDeleteNotOwned_Forbidden() throws Exception {
    mockMvc.perform(delete("/user/{id}", testAdminOffer.getId()).
            with(csrf())
        ).
        andExpect(status().isForbidden());
  }

  @WithUserDetails(value = "ivan@example.com",
    userDetailsServiceBeanName = "testUserDataService")
  @Test
  void testAddComment() throws Exception {

    mockMvc.perform(post("/comment").
            param("email", "ivan@example.com").
            param("message", "Hello!").
            with(csrf())
        ).
        andExpect(status().is3xxRedirection()).
        andExpect(redirectedUrl("/comment"));
  }
}
