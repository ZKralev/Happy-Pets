package bg.softuni.HappyCats.model.mapper;

import bg.softuni.HappyCats.model.DTOS.AddPetsDTO;
import bg.softuni.HappyCats.model.DTOS.PetsDTO;
import bg.softuni.HappyCats.model.DTOS.UserDetailDTO;
import bg.softuni.HappyCats.model.entity.Pets;
import bg.softuni.HappyCats.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface PetsMapper {

    @Mapping(target = "active", constant = "true")
    Pets petsMapperDTO(AddPetsDTO addPetsDTO);

    @Mapping(target = "active", constant = "true")
    PetsDTO petsEntityToPetsDetailDto(Pets pets);

}