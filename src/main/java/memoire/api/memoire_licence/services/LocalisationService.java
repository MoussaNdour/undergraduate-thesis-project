package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.LocalisationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LocalisationService {
    public List<LocalisationDTO> findAll() {

        return new ArrayList<>();
    }

    public LocalisationDTO findById(int idlocal) {
        return new LocalisationDTO();
    }

    public boolean create(LocalisationDTO localisationDTO) {
        return false;
    }

    public void save(int idlocal, LocalisationDTO localisationDTO) {

    }

    public boolean update(LocalisationDTO localisationDTO) {
        return false;
    }

    public boolean partialUpdate(int idlocal, LocalisationDTO localisationDTO) {
        return false;
    }

    public boolean deleteById(int idlocal) {
        return false;
    }
}
