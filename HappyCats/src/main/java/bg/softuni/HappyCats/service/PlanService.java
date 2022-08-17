package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.entity.Plan;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.repository.PlanRepository;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public PlanService(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    public void addPlanBasic() {

        Plan plan = planRepository.getReferenceById(1L);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> user = userRepository.findByUsername(username);
        user.get().setPlans(plan);

        userRepository.save(user.get());
    }

    public void addPlanStandard() {

        Plan plan = planRepository.getReferenceById(2L);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> user = userRepository.findByUsername(username);
        user.get().setPlans(plan);

        userRepository.save(user.get());
    }

    public void addPlanPremium() {

        Plan plan = planRepository.getReferenceById(3L);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> user = userRepository.findByUsername(username);
        user.get().setPlans(plan);

        userRepository.save(user.get());
    }
}
