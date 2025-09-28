package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.AdministrateurDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministrateurService {
    public List<AdministrateurDTO> findAll() {

        return new ArrayList<>();
    }

    public AdministrateurDTO findById(int idutilisateur, int idadmin) {
        return new AdministrateurDTO();
    }

    public boolean create(AdministrateurDTO administrateurDTO) {
        return false;
    }

    public void save(int idutilisateur, int idadmin, AdministrateurDTO administrateurDTO) {

    }

    public boolean update(AdministrateurDTO administrateurDTO) {

        return false;
    }

    public boolean partialUpdate(int idutilisateur, int idadmin, AdministrateurDTO administrateurDTO) {

        return false;
    }

    public boolean deleteById(int idutilisateur, int idadmin) {
        return false;
    }
}
