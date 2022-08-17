package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.entity.Booking;
import bg.softuni.HappyCats.model.entity.Pets;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    testUser = testDataUtils.createTestUser("ivan");
    testAdmin = testDataUtils.createTestAdmin("zdravko");
  }

  @AfterEach
  void tearDown() {
    testDataUtils.cleanUpDatabase();
  }

  @Test
  @WithMockUser(
          username = "zdravko",
          password = "4b148b365433c559fdc07a0742712e88b61d5e23a52bb10206c308908e2e67836ecb3ff5714006ea"
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
                    with(csrf())
            ).
            andExpect(status().is3xxRedirection()).
            andExpect(redirectedUrl("/pets"));
  }

  @Test
  @WithMockUser(
          username = "stoyan",
          password = "stoyan",
          roles = "USER"
  )
  void testInvalidDataOnAgeAddPet() throws Exception {
    mockMvc.perform(post("/pets").
                    param("name", "ivancho").
                    param("age", "-22").
                    param("kind", "Cat").
                    param("breed", "British").
                    with(csrf())
            ).
            andExpect(status().is3xxRedirection()).
            andExpect(redirectedUrl("/pets"));
  }

  @Test
  @WithMockUser(
          username = "stoyan",
          password = "stoyan",
          roles = "USER"
  )
  void testInvalidDataOnNameAddPet() throws Exception {
    mockMvc.perform(post("/pets").
                    param("name", (String) null).
                    param("age", "-2").
                    param("kind", "Cat").
                    param("breed", "British").
                    with(csrf())
            ).
            andExpect(status().is3xxRedirection()).
            andExpect(redirectedUrl("/pets"));
  }

  @Test
  @WithMockUser(
          username = "stoyan",
          password = "stoyan",
          roles = "USER"
  )
  void testAdminPageWithoutAdminRights() throws Exception {
    mockMvc.perform(get("/admin"))
            .andExpect(status().is4xxClientError());

  }

  @Test
  void testAdminPageWithoutUser() throws Exception {
    mockMvc.perform(get("/admin"))
            .andExpect(status().is3xxRedirection()).
            andExpect(redirectedUrl("http://localhost/login"));

  }

  @Test
  void testBookingPageWithoutUser() throws Exception {
    mockMvc.perform(get("/booking"))
            .andExpect(status().is3xxRedirection()).
            andExpect(redirectedUrl("http://localhost/login"));

  }

  @Test
  @WithMockUser(
          username = "zdravko",
          password = "admin",
          roles = "ADMIN"
  )
  void testAdminPageWithAdminRights() throws Exception {
    mockMvc.perform(get("/users"))
            .andExpect(status().isOk());
  }

  @Test
  void testServicePageWithoutUser() throws Exception {
    mockMvc.perform(get("/service"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
  }

  @Test
  void testPricePageWithoutUser() throws Exception {
    mockMvc.perform(get("/price"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
  }

  @Test
  void testAboutPageWithoutUser() throws Exception {
    mockMvc.perform(get("/about"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
  }

  @Test
  @WithMockUser(
          username = "zdravko",
          password = "admin",
          roles = "ADMIN"
  )
  public void when_getAllUsers() throws Exception {
    mockMvc
            .perform(MockMvcRequestBuilders
                    .get("/users"))
            .andExpect(status().isOk())
            .andExpect(view().name("admin"))
            .andExpect(model().attributeExists("users"));
  }



}
