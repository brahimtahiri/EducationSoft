package fr.brahimtahiri.educationsoft.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDTO {

    @Size(message = "La taille de la dénomination doit être comprise entre 1 et 64 caractères.", min = 1, max = 64)
    private String denomination;

}
