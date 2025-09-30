package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.response.PrestataireDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PrestataireService {
    public List<PrestataireDTO> findAll() {
        return new ArrayList<>();
    }

    public PrestataireDTO findById(int idutilisateur, String idprestataire) {
        return new PrestataireDTO();
    }

    public boolean create(PrestataireDTO prestataireDTO) {
        return false;
    }

    public void save(int idutilisateur, String idprestataire, PrestataireDTO prestataireDTO) {
    }

    public boolean update(PrestataireDTO prestataireDTO) {
        return false;
    }

    public boolean partialUpdate(int idutilisateur, String idprestataire, PrestataireDTO prestataireDTO) {
        return false;
    }

    public boolean deleteById(int idutilisateur, String idprestataire) {
        return false;
    }
}
