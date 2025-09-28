package memoire.api.memoire_licence.controllers;


import memoire.api.memoire_licence.dto.LoginDTO;
import memoire.api.memoire_licence.dto.request.RegisterUserDTO;
import memoire.api.memoire_licence.entities.Administrateur;
import memoire.api.memoire_licence.entities.Client;
import memoire.api.memoire_licence.entities.Prestataire;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.services.LoginAndRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public")
public class LoginAndRegisterController {

    @Autowired
    LoginAndRegistrationService service;

    @PostMapping("/register/client")
    public ResponseEntity<String> registerClient(RegisterUserDTO registerUserDTO){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setPrenom(registerUserDTO.getPrenom());
        utilisateur.setNom(registerUserDTO.getNom());
        utilisateur.setTelephone(registerUserDTO.getTelephone());
        utilisateur.setMotdepasse(registerUserDTO.getMot_de_passe());
        utilisateur.setEmail(registerUserDTO.getEmail());
        utilisateur.setAdresse(registerUserDTO.getAdresse());
        utilisateur.setRole("CLIENT");

        Utilisateur utilisateursaved=service.save(utilisateur);

        Client client=new Client();



        return ResponseEntity.ok("Le client est bien enregistrer dans la base de donnees");
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(RegisterUserDTO registerUserDTO){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setPrenom(registerUserDTO.getPrenom());
        utilisateur.setNom(registerUserDTO.getNom());
        utilisateur.setTelephone(registerUserDTO.getTelephone());
        utilisateur.setMotdepasse(registerUserDTO.getMot_de_passe());
        utilisateur.setEmail(registerUserDTO.getEmail());
        utilisateur.setAdresse(registerUserDTO.getAdresse());
        utilisateur.setRole("ADMIN");

        Utilisateur utilisateursaved=service.save(utilisateur);

        Administrateur admin=new Administrateur();


        service.saveAdmin(admin);

        return ResponseEntity.ok("L'admin a bien ete ajouter est bien enregistrer dans la base de donnees");

    }

    @PostMapping("/register/prestataire")
    public ResponseEntity<String> registerPrestataire(RegisterUserDTO registerUserDTO){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setPrenom(registerUserDTO.getPrenom());
        utilisateur.setNom(registerUserDTO.getNom());
        utilisateur.setTelephone(registerUserDTO.getTelephone());
        utilisateur.setMotdepasse(registerUserDTO.getMot_de_passe());
        utilisateur.setEmail(registerUserDTO.getEmail());
        utilisateur.setAdresse(registerUserDTO.getAdresse());
        utilisateur.setRole("PRESTATAIRE");

        Utilisateur utilisateursaved=service.save(utilisateur);

        Prestataire prestataire=new Prestataire();



        return ResponseEntity.ok("Le client est bien enregistrer dans la base de donnees");

    }



    public ResponseEntity<?> Login(LoginDTO loginDTO){
        Utilisateur utilisateur=service.getUser(loginDTO);

        if(utilisateur!=null){
            return ResponseEntity.ok(utilisateur);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le nom d'utilisateur ou le mot de passe est incorrecte");
        }
    }

}
