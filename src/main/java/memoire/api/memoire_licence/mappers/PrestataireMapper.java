package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.response.PrestataireDTO;
import memoire.api.memoire_licence.entities.Prestataire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrestataireMapper {

    Prestataire toEntity(PrestataireDTO prestataireDTO);

    PrestataireDTO toDTO(Prestataire prestataire);
}
