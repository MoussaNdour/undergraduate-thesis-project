package memoire.api.memoire_licence.controllers;



import memoire.api.memoire_licence.dto.request.LoginUtilisateurDTO;
import memoire.api.memoire_licence.dto.request.RegisterUtilisateurDTO;
import memoire.api.memoire_licence.dto.response.AdministrateurDTO;
import memoire.api.memoire_licence.dto.response.ClientDTO;
import memoire.api.memoire_licence.dto.response.PrestataireDTO;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.services.interfaces.LoginInterface;
import memoire.api.memoire_licence.services.interfaces.RegistrationInterface;
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
    LoginInterface loginservice;

    @Autowired
    RegistrationInterface registrationservice;

    @PostMapping("/register/client")
    public ResponseEntity<?> registerClient(RegisterUtilisateurDTO registerUser){

        ClientDTO client=registrationservice.registerClient(registerUser);

        return ResponseEntity.ok(client);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(RegisterUtilisateurDTO registerUser){

        AdministrateurDTO admin =registrationservice.registerAdmin(registerUser);

        return ResponseEntity.ok(admin);

    }

    @PostMapping("/register/prestataire")
    public ResponseEntity<?> registerPrestataire(RegisterUtilisateurDTO registerUser){

        PrestataireDTO prestataire=registrationservice.registerPrestataie(registerUser);

        return ResponseEntity.ok(prestataire);
    }


    @PostMapping("/login")
    public ResponseEntity<?> Login(LoginUtilisateurDTO user){
        Utilisateur utilisateur=loginservice.getUser(user);
        if (utilisateur==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le nom d'utilisateur ou le mot de passe est incorrecte");
        }

        return ResponseEntity.ok("ok, you are connected");
    }

}