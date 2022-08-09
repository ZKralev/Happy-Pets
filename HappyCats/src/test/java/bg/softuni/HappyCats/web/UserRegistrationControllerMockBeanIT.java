package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerMockBeanIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmailService mockEmailService;

  @Test
  void testRegistrationPageShown() throws Exception {
    mockMvc.perform(get("/register")).
        andExpect(status().isOk()).
        andExpect(view().name("register"));
  }

  @Test
  void testUserRegistration() throws Exception {
    mockMvc.perform(post("/register").
                    param("username", "annaa").
                    param("fullName", "Anna Ivanova").
                    param("email", "anna@example.com").
                    param("password", "123123").
                    param("confirmPassword", "123123").
                    with(csrf())
    ).
        andExpect(status().is3xxRedirection()).
        andExpect(redirectedUrl("/"));

    verify(mockEmailService).
        sendRegistrationEmail("anna@example.com",
            "annaa",
            Locale.ENGLISH);
  }
}
