package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.AddPetsDTO;
import bg.softuni.HappyCats.model.DTOS.PetsDTO;
import bg.softuni.HappyCats.model.entity.Pets;
import bg.softuni.HappyCats.model.entity.User;
import bg.softuni.HappyCats.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.Optional;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-07-14T20:41:43+0300",
        comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class PetsMapperImpl implements PetsMapper {

    private UserRepository userRepository;

    public PetsMapperImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Pets petsMapperDTO(AddPetsDTO addPetsDTO) {
        if ( addPetsDTO == null ) {
            return null;
        }

        Pets pet = new Pets();
        pet.setAge(addPetsDTO.getAge());
        pet.setKind(addPetsDTO.getKind());
        pet.setBreed(addPetsDTO.getBreed());
        pet.setName(addPetsDTO.getName());
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> user = userRepository.findByUsername(username);
        pet.setOwner(user.get());

        return pet;
    }

    @Override
    public PetsDTO petsEntityToPetsDetailDto(Pets pets) {
        if ( pets == null ) {
            return null;
        }

        PetsDTO petsDTO = new PetsDTO();

        petsDTO.setAge(pets.getAge());
        petsDTO.setBreed(pets.getBreed());
        petsDTO.setKind(pets.getKind());
        petsDTO.setName(pets.getName());
        petsDTO.setOwner(pets.getOwner());

        return petsDTO;
    }
}
