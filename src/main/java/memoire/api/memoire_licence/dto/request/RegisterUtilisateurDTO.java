package memoire.api.memoire_licence.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterUtilisateurDTO {

    @NotNull(message="Le nom de l'utilisateur ne peux pas etre nul")
    private String nom ;

    @NotNull(message = "Le prenom de l'utilisateur ne peux pas etre nul")
    private String prenom ;

    @NotNull(message ="Le numero de telephone de l'utilisateur ne peux pas etre nul")
    private Long telephone ;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email invalide")
    private String email ;

    @NotNull(message = "L'adresse de l'utilisateur ne peux pas etre nul")
    private String adresse ;

   @Size(min = 8,message = "Le mot de passe doit au moins etre de 8 characteres")
    private String motdepasse ;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
