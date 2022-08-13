package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.AddPetsDTO;
import bg.softuni.HappyCats.model.DTOS.PetsDTO;
import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.entity.Pets;
import bg.softuni.HappyCats.model.mapper.PetsMapper;
import bg.softuni.HappyCats.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetsService {


    private final PetsRepository petsRepository;
    private final PetsMapper petsMapper;


    @Autowired
    public PetsService(PetsRepository petsRepository, PetsMapper petsMapper) {
        this.petsRepository = petsRepository;
        this.petsMapper = petsMapper;
    }

    public void addPets(AddPetsDTO addPetsDTO, HappyPetsUserDetailsService userDetails) {
        Pets newPet = petsMapper.petsMapperDTO(addPetsDTO);
        petsRepository.save(newPet);
    }


    public Page<PetsDTO> getAllPets(Pageable pageable) {
        return petsRepository.
                findAll(pageable).
                map(petsMapper::petsEntityToPetsDetailDto);
    }
}
