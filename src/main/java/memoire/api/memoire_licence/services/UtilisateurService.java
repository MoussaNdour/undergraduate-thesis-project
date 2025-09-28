package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.UtilisateurDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {
    public List<UtilisateurDTO> findAll() {
        return new ArrayList<>();
    }

    public UtilisateurDTO findById(int idutilisateur) {
        return new UtilisateurDTO();
    }

    public boolean create(UtilisateurDTO utilisateurDTO) {
        return false;
    }

    public void save(int idutilisateur, UtilisateurDTO utilisateurDTO) {
    }

    public boolean update(UtilisateurDTO utilisateurDTO) {
        return false;
    }

    public boolean partialUpdate(int idutilisateur, UtilisateurDTO utilisateurDTO) {
        return false;
    }

    public boolean deleteById(int idutilisateur) {
        return false;
    }
}
