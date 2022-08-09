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
import org.springframework.test.web.servlet.MockMvc;

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
    mockMvc.perform(delete("/offers/{id}", testOffer.getId()).
            with(csrf())
        ).
        andExpect(status().is3xxRedirection());
        //TODO: check redirection url to login w/o schema
  }

  @Test
  @WithMockUser(
      username = "admin@example.com",
      roles = {"ADMIN", "USER"}
  )
  void testDeleteByAdmin() throws Exception {
    mockMvc.perform(delete("/offers/{id}", testOffer.getId()).
            with(csrf())
        ).
        andExpect(status().is3xxRedirection()).
        andExpect(view().name("redirect:/offers/all"));
  }

  @WithMockUser(
      username = "user@example.com",
      roles = "USER"
  )
  @Test
  void testDeleteByOwner() throws Exception {
    mockMvc.perform(delete("/offers/{id}", testOffer.getId()).
            with(csrf())
        ).
        andExpect(status().is3xxRedirection()).
        andExpect(view().name("redirect:/offers/all"));
  }

  @WithMockUser(
      username = "user@example.com",
      roles = "USER"
  )
  @Test
  public void testDeleteNotOwned_Forbidden() throws Exception {
    mockMvc.perform(delete("/offers/{id}", testAdminOffer.getId()).
            with(csrf())
        ).
        andExpect(status().isForbidden());
  }

  @WithUserDetails(value = "user@example.com",
    userDetailsServiceBeanName = "testUserDataService")
  @Test
  void testAddOffer() throws Exception {

    mockMvc.perform(post("/offers/add").
            param("modelId", "1").
            param("price", "11200").
            param("engine", "GASOLINE").
            param("year", "1979").
            param("mileage", "1000").
            param("description", "test").
            param("transmission", "MANUAL").
            param("imageUrl", "image://test.png").
            with(csrf())
        ).
        andExpect(status().is3xxRedirection()).
        andExpect(redirectedUrl("/offers/all"));
  }
}
