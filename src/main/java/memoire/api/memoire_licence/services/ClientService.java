package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.AdministrateurDTO;
import memoire.api.memoire_licence.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    public List<ClientDTO> findAll() {

        return new ArrayList<>();
    }

    public ClientDTO findById(int idutilisateur, int idadmin) {
        return new ClientDTO();
    }

    public boolean create(ClientDTO clientDTO) {
        return false;
    }

    public void save(int idutilisateur, int idadmin, ClientDTO clientDTO) {

    }

    public boolean update(ClientDTO clientDTO) {

        return false;
    }

    public boolean partialUpdate(int idutilisateur, int idadmin, ClientDTO clientDTO) {

        return false;
    }

    public boolean deleteById(int idutilisateur, int idadmin) {
        return false;
    }
}
