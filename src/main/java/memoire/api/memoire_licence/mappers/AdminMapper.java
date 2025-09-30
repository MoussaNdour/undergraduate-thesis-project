package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.response.AdministrateurDTO;
import memoire.api.memoire_licence.entities.Administrateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Administrateur toEntity(AdministrateurDTO dto);

    AdministrateurDTO toDTO(Administrateur admin);
}
