package bg.softuni.HappyCats.web;


import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.model.DTOS.CommentDetailsDTO;
import bg.softuni.HappyCats.model.entity.Booking;
import bg.softuni.HappyCats.model.entity.Comment;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class CommentControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentsController mocController;

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
    public void getCommentPage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/comment",
                String.class)).contains("Our Services");
    }

    @Test
    @WithMockUser(
            username = "stoyan",
            password = "stoyan",
            roles = "USER"
    )
    public void openCommentPage() throws Exception {
        mockMvc.perform(get("/comment"))
                .andExpect(status().isOk());
    }

    @Test
    public void openCommentWithoutUserAccount() throws Exception {
        mockMvc.perform(get("/comment"))
                .andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(
            username = "zdravko",
            password = "4b148b365433c559fdc07a0742712e88b61d5e23a52bb10206c308908e2e67836ecb3ff5714006ea"
    )
    void testAddComment() throws Exception {
        AddCommentDTO addCommentDTO = new AddCommentDTO();
        addCommentDTO.setMessage("IVANE BE");

        System.out.println(addCommentDTO.getMessage());


        Authentication authentication = Mockito.mock(Authentication.class);
// Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(post("/comment").param("message", "hello"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testComment() throws Exception {
        Comment comment = new Comment();
        comment.setMessage("Hello");
        comment.setId(22);
        comment.setAuthor(testUser);
        comment.setCreated();

        System.out.println(comment.getAuthor());
        System.out.println(comment.getId());
        System.out.println(comment.getCreated());
        System.out.println(comment.getMessage());


        CommentDetailsDTO dto = new CommentDetailsDTO();
        dto.setAuthor(testUser);
        dto.setCreated(LocalDateTime.now());
        dto.setMessage("Hello");

        System.out.println(dto.getAuthor());
        System.out.println(dto.getCreated());
        System.out.println(dto.getMessage());
    }




}