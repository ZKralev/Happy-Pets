package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.AddPetsDTO;
import bg.softuni.HappyCats.model.entity.Pets;
import bg.softuni.HappyCats.model.mapper.PetsMapper;
import bg.softuni.HappyCats.repository.PetsRepository;
import org.springframework.stereotype.Service;

@Service
public class PetsService {


    private final PetsRepository petsRepository;
    private final PetsMapper petsMapper;


    public PetsService(PetsRepository petsRepository, PetsMapper petsMapper) {
        this.petsRepository = petsRepository;
        this.petsMapper = petsMapper;
    }

    public void addPets(AddPetsDTO addPetsDTO, HappyPetsUserDetailsService userDetails) {
        Pets newPet = petsMapper.petsMapperDTO(addPetsDTO);
        petsRepository.save(newPet);
    }
}
