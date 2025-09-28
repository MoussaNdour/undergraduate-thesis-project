package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.LoginDTO;
import memoire.api.memoire_licence.entities.Administrateur;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.repositories.AdministrateurRepository;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginAndRegistrationService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    AdministrateurRepository adminRepos;

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }



    public Utilisateur getUser(LoginDTO loginDTO) {
        Utilisateur utilisateur=utilisateurRepository.findByEmail(loginDTO.getEmail()).orElse(null);

        if(encoder.matches(loginDTO.getMot_de_passe(), utilisateur.getMotdepasse())){
            return utilisateur;
        }
        else{
            return null;
        }

    }

    public void saveAdmin(Administrateur admin) {
        admin.setCodedaccess(2025);
        adminRepos.save(admin);
    }
}
