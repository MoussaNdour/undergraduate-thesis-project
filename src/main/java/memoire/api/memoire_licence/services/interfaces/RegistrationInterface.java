package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.RegisterUtilisateurDTO;
import memoire.api.memoire_licence.dto.response.AdministrateurDTO;
import memoire.api.memoire_licence.dto.response.ClientDTO;
import memoire.api.memoire_licence.dto.response.PrestataireDTO;
import memoire.api.memoire_licence.entities.Administrateur;
import memoire.api.memoire_licence.entities.Utilisateur;

public interface RegistrationInterface {

    ClientDTO registerClient(RegisterUtilisateurDTO dto);

    AdministrateurDTO registerAdmin(RegisterUtilisateurDTO dto);

    PrestataireDTO registerPrestataie(RegisterUtilisateurDTO dto);
}
