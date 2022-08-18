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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookingControllerIT {

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
    public void getBookingPage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/booking",
                String.class)).contains("Our Services");
    }

    @Test
    @WithMockUser(
            username = "stoyan",
            password = "stoyan",
            roles = "USER"
    )
    public void openBookingPage() throws Exception {
        mockMvc.perform(get("/booking"))
                .andExpect(status().isOk());
    }

    @Test
    public void openBookingWithoutUserAccount() throws Exception {
        mockMvc.perform(get("/booking"))
                .andExpect(status().is3xxRedirection()).
        andExpect(redirectedUrl("http://localhost/login"));
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
                        param("service", "BOARDING").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/booking"));
    }

    @Test
    void createBooking() throws Exception{
        Booking booking = new Booking();
        booking.setUser(testUser);
        booking.setId(12L);
        booking.setEmail(testUser.getEmail());
        booking.setName("ivancho");
        booking.setService(1);

        System.out.println(booking.getEmail());
        System.out.println(booking.getName());
        System.out.println(booking.getId());
        System.out.println(booking.getUser());
        System.out.println(booking.getService());
        System.out.println(booking.getReservationDateTime());
    }
}