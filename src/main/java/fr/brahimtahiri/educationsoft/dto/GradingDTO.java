package fr.brahimtahiri.educationsoft.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradingDTO {

    @Size(message = "La taille du type doit être comprise entre 1 et 32 caractères.", min = 1, max = 32)
    private String type;


    @Min(message = "La note doit être supérieure à 0", value = 0)
    @Max(message = "La note doit être inférieure à 20", value = 20)
    private Float grade;

    @NotNull(message = "La matière doit être renseignée.")
    private Long subjectId;

    @NotNull(message = "L'étudiant doit être renseignée.")
    private Long studentId;

}
