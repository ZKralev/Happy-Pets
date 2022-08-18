package bg.softuni.HappyCats.web;


import bg.softuni.HappyCats.model.DTOS.PetsDTO;
import bg.softuni.HappyCats.model.entity.Pets;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.model.enums.PetsKind;
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
public class PetsControllerIT {

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
    public void getServicePage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/service",
                String.class)).contains("Our Services");
    }

    @Test
    @WithMockUser(
            username = "stoyan",
            password = "stoyan",
            roles = "USER"
    )
    public void openAllPetsPage() throws Exception {
        mockMvc.perform(get("/all-pets"))
                .andExpect(status().isOk());
    }

    @Test
    public void openAllPetsWithoutUserAccount() throws Exception {
        mockMvc.perform(get("/all-pets"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(
            username = "stoyan",
            password = "stoyan",
            roles = "USER"
    )
    public void openAddPetPage() throws Exception {
        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk());
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
    void createPet() throws Exception{
        Pets pet = new Pets();
        pet.setAge(2);
        pet.setId(12);
        pet.setBreed("Kuchenche");
        pet.setKind(1);
        pet.setName("ivancho");
        pet.setOwner(testUser);

        System.out.println(pet.getAge());
        System.out.println(pet.getId());
        System.out.println(pet.getName());
        System.out.println(pet.getBreed());
        System.out.println(pet.getKind());
        System.out.println(pet.getOwner());


        PetsDTO dto = new PetsDTO();
        dto.setOwner(testUser);
        dto.setName("ivan");
        dto.setKind(PetsKind.DOG);
        dto.setAge(2);
        dto.setBreed("doggy");

        System.out.println(dto.getAge());
        System.out.println(dto.getBreed());
        System.out.println(dto.getName());
        System.out.println(dto.getKind());
        System.out.println(dto.getOwner());
    }
}