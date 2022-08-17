package bg.softuni.HappyCats.components;

import bg.softuni.HappyCats.model.entity.Plan;
import bg.softuni.HappyCats.model.enums.PlanEnum;
import bg.softuni.HappyCats.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class InitializePetCarePlans implements CommandLineRunner {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public void run(String... args) throws Exception {

        Plan basic = new Plan();
        basic.setPlanEnum(PlanEnum.BASIC);
        basic.setPrice(49);
        basic.setId(1L);

        planRepository.save(basic);

        Plan standard = new Plan();
        standard.setPlanEnum(PlanEnum.STANDARD);
        standard.setPrice(99);
        standard.setId(2L);

        planRepository.save(standard);

        Plan premium = new Plan();
        premium.setPlanEnum(PlanEnum.PREMIUM);
        premium.setPrice(149);
        premium.setId(3L);

        planRepository.save(premium);
    }
}
