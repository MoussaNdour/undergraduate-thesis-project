package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.response.ContratDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContratService {

    public List<ContratDTO> findAll() {

        return new ArrayList<>();
    }

    public ContratDTO findById(int idcontrat) {
        return new ContratDTO();
    }

    public boolean create(ContratDTO contratDTO) {
        return false;
    }

    public void save(int idcontrat, ContratDTO contratDTO) {

    }

    public boolean update(ContratDTO contratDTO) {

        return false;
    }

    public boolean partialUpdate(int idcontrat, ContratDTO contratDTO) {

        return false;
    }

    public boolean deleteById(int idcontrat) {
        return false;
    }
}
