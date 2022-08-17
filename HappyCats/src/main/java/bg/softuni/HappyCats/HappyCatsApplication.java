package bg.softuni.HappyCats;

import bg.softuni.HappyCats.model.entity.Plan;
import bg.softuni.HappyCats.model.enums.PlanEnum;
import bg.softuni.HappyCats.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
public class HappyCatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyCatsApplication.class, args);
    }

}

