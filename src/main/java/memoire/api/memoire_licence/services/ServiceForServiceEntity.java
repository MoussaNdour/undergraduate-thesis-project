package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.ServiceDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceForServiceEntity {
    public List<ServiceDTO> findAll() {
        return new ArrayList<>();
    }

    public ServiceDTO findById(int idservice) {
        return new ServiceDTO();
    }

    public boolean create(ServiceDTO serviceDTO) {
        return false;
    }

    public void save(int idservice, ServiceDTO serviceDTO) {
    }

    public boolean update(ServiceDTO serviceDTO) {
        return false;
    }

    public boolean partialUpdate(int idservice, ServiceDTO serviceDTO) {
        return false;
    }

    public boolean deleteById(int idservice) {
        return false;
    }
}
