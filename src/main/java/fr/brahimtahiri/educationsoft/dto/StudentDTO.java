package fr.brahimtahiri.educationsoft.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentDTO {

    @Size(message = "La taille du prénom doit être comprise entre 1 et 64 caractères.", min = 1, max = 64)
    private String firstname;

    @Size(message = "La taille du nom doit être comprise entre 1 et 64 caractères.", min = 1, max = 64)
    private String lastname;

    @Email(message = "L'adresse email doit être valide.")
    private String email;

    @Size(message = "La taille de l'adresse doit être comprise entre 1 et 64 caractères.", min = 1, max = 64)
    private String address;

    @Size(message = "La taille du numéro de téléphone ne doit pas dépasser 15 chiffres.", max = 15)
    private String phoneNumber;

    @Size(message = "La taille de l'identifiant doit être comprise entre 1 et 34 caractères.", min = 1, max = 34)
    private String username;

    @Size(message = "La taille du mot de passe doit être comprise entre 8 et 255 caractères.", min = 8, max = 255)
    private String password;

    @NotNull(message = "La date de naissance doit être renseignée.")
    private Date dateOfBirth;

    private Long classgroupId;

}
