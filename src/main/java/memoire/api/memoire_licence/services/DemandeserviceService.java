package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.DemandeserviceDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DemandeserviceService {
    public List<DemandeserviceDTO> findAll() {
        return new ArrayList<>();
    }

    public DemandeserviceDTO findById(int iddemande) {
        return new DemandeserviceDTO();
    }

    public boolean create(DemandeserviceDTO demandeserviceDTO) {
        return false;
    }

    public void save(int iddemande, DemandeserviceDTO demandeserviceDTO) {

    }

    public boolean update(DemandeserviceDTO demandeserviceDTO) {
        return false;
    }

    public boolean partialUpdate(int iddemande, DemandeserviceDTO demandeserviceDTO) {
        return false;
    }

    public boolean deleteById(int iddemande) {
        return false;
    }
}
