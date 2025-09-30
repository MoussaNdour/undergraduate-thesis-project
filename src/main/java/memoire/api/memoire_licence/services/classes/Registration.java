package memoire.api.memoire_licence.services.classes;

import jakarta.transaction.Transactional;
import memoire.api.memoire_licence.dto.request.RegisterUtilisateurDTO;
import memoire.api.memoire_licence.dto.response.AdministrateurDTO;
import memoire.api.memoire_licence.dto.response.ClientDTO;
import memoire.api.memoire_licence.dto.response.PrestataireDTO;
import memoire.api.memoire_licence.entities.Administrateur;
import memoire.api.memoire_licence.entities.Client;
import memoire.api.memoire_licence.entities.Prestataire;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.mappers.*;
import memoire.api.memoire_licence.repositories.AdministrateurRepository;
import memoire.api.memoire_licence.repositories.ClientRepository;
import memoire.api.memoire_licence.repositories.PrestataireRepository;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import memoire.api.memoire_licence.services.interfaces.RegistrationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Registration implements RegistrationInterface {

    @Autowired
    UtilisateurRepository utilisateurRepos;
    
    @Autowired
    ClientRepository clientRepos;

    @Autowired
    PrestataireRepository prestataireRepos;

    @Autowired
    AdministrateurRepository adminRepos;
    
    @Autowired
    ClientMapper clientMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PrestataireMapper prestataireMapper;
    
    @Autowired
    RegisterUtilisateurMapper registermapper;

    @Autowired
    PasswordEncoder encoder;
    

    @Override
    @Transactional
    public ClientDTO registerClient(RegisterUtilisateurDTO dto) {
        Utilisateur utilisateur=registermapper.toEntity(dto);
        utilisateur.setRole("CLIENT");
        utilisateur.setMotdepasse(encoder.encode(utilisateur.getMotdepasse()));
        Utilisateur saved=utilisateurRepos.save(utilisateur);
        Client client=new Client();
        client.setUtilisateur(saved);
        
        Client clientsaved=clientRepos.save(client);

        ClientDTO clientDTO=clientMapper.toDTO(clientsaved);
        clientDTO.setUtilisateur(saved);
        clientDTO.initialise();
        
        return clientDTO;
    }

    @Override
    @Transactional
    public AdministrateurDTO registerAdmin(RegisterUtilisateurDTO dto) {
        Utilisateur utilisateur=registermapper.toEntity(dto);
        utilisateur.setRole("ADMIN");
        utilisateur.setMotdepasse(encoder.encode(utilisateur.getMotdepasse()));
        Utilisateur saved=utilisateurRepos.save(utilisateur);
        
        Administrateur admin=new Administrateur();
        admin.setUtilisateur(saved);
        admin.setCodedaccess(2001);
        Administrateur adminsaved=adminRepos.save(admin);
        
        
        return adminMapper.toDTO(adminsaved);
    }

    @Override
    @Transactional
    public PrestataireDTO registerPrestataie(RegisterUtilisateurDTO dto) {
        Utilisateur utilisateur=registermapper.toEntity(dto);
        utilisateur.setRole("PRESTATAIRE");
        utilisateur.setMotdepasse(encoder.encode(utilisateur.getMotdepasse()));
        Utilisateur saved=utilisateurRepos.save(utilisateur);

        Prestataire prestataire=new Prestataire();
        prestataire.setUtilisateur(saved);

        Prestataire prestatairesaved=prestataireRepos.save(prestataire);
        
        return prestataireMapper.toDTO(prestatairesaved);
    }
}
