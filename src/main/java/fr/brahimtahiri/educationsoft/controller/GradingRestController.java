package fr.brahimtahiri.educationsoft.controller;

import fr.brahimtahiri.educationsoft.dto.GradingDTO;
import fr.brahimtahiri.educationsoft.entity.Grading;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.service.GradingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/gradings")
@AllArgsConstructor
public class GradingRestController {

    private final GradingService gradingService;

    /**
     * Récupère une notation en fonction de son identifiant.
     *
     * @param id l'identifiant unique de l'évaluation à récupérer
     * @return l'objet {@link Grading} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucune évaluation n'est trouvée avec l'identifiant donné
     */
    @GetMapping(path = "/{id}")
    public Grading getGradingById(@PathVariable Long id) throws ResourceNotFoundException {
        return this.gradingService.getGradingById(id);
    }

    /**
     * Crée une nouvelle notation à partir des données fournies.
     *
     * @param newGrading un objet {@link GradingDTO} contenant les informations nécessaires à l'ajout d'une notation.
     *                   L'objet est validé avant traitement.
     * @return l'instance de la classe {@link Grading} nouvellement créée
     * @throws ResourceNotFoundException si une erreur survient lors de la création
     */
    @PostMapping
    public Grading createGrading(@RequestBody @Valid GradingDTO newGrading) throws ResourceNotFoundException {
        return this.gradingService.createGrading(newGrading);
    }

    /**
     * Met à jour une notation existante avec les nouvelles données fournies.
     *
     * @param id         l'identifiant de l'évaluation à mettre à jour
     * @param newGrading un objet {@link GradingDTO} contenant les nouvelles informations de la notation.
     *                   L'objet est validé avant traitement.
     * @return l'instance de la classe {@link Grading} mise à jour
     * @throws ResourceNotFoundException si aucune évaluation n'est trouvée avec l'identifiant fourni
     */
    @PutMapping(path = "/{id}")
    public Grading updateGrading(@PathVariable Long id, @RequestBody @Valid GradingDTO newGrading) throws ResourceNotFoundException {
        return this.gradingService.updateGrading(id, newGrading);
    }

    /**
     * Supprime une notation en fonction de son identifiant.
     *
     * @param id l'identifiant de la notation à supprimer
     * @return une réponse HTTP avec le statut {@code 204 No Content} si la suppression a réussi
     * @throws ResourceNotFoundException si aucune évaluation n'est trouvée avec l'identifiant fourni
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteGrading(@PathVariable Long id) throws ResourceNotFoundException {
        this.gradingService.deleteGrading(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Récupère toutes les notations associées à un étudiant spécifique.
     *
     * @param studentId l'identifiant unique de l'étudiant dont les évaluations doivent être récupérées
     * @return une liste contenant toutes les évaluations associées à l'étudiant spécifié
     */
    @GetMapping(path = "/student/{studentId}")
    public List<Grading> getGradingsByStudentId(@PathVariable Long studentId) {
        return this.gradingService.getAllGradingByStudentId(studentId);
    }

}
