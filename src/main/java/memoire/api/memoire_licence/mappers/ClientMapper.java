package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.response.ClientDTO;
import memoire.api.memoire_licence.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);

    Client toEntity(ClientDTO clientDTO);
}
