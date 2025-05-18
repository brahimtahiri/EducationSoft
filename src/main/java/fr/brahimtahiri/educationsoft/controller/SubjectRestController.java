package fr.brahimtahiri.educationsoft.controller;

import fr.brahimtahiri.educationsoft.dto.SubjectDTO;
import fr.brahimtahiri.educationsoft.entity.Subject;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.service.SubjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/subjects")
@AllArgsConstructor
public class SubjectRestController {

    public final SubjectService subjectService;

    /**
     * Récupère la liste de toutes les matières.
     *
     * @return une liste contenant tous les objets {@link Subject}.
     */
    @GetMapping
    public List<Subject> getAllSubjects() {
        return this.subjectService.getAllSubjects();
    }

    /**
     * Récupère une matière en fonction de son identifiant.
     *
     * @param id l'identifiant unique d'une matière à récupérer
     * @return l'objet {@link Subject} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucune matière n'est trouvée avec l'identifiant donné
     */
    @GetMapping(path = "/{id}")
    public Subject getSubjectById(@PathVariable Long id) throws ResourceNotFoundException {
        return this.subjectService.getSubjectById(id);
    }

    /**
     * Crée une nouvelle matière à partir des données fournies.
     *
     * @param newSubject un objet {@link SubjectDTO} contenant les informations nécessaires à la création de la matière.
     *                   L'objet est validé avant traitement.
     * @return l'instance de la matière {@link Subject} nouvellement créée.
     */
    @PostMapping
    public Subject createSubject(@RequestBody @Valid SubjectDTO newSubject) {
        return this.subjectService.createSubject(newSubject);
    }

    /**
     * Met à jour une matière existante avec les nouvelles données fournies.
     *
     * @param id             l'identifiant de la matière à mettre à jour
     * @param updatedSubject un objet {@link SubjectDTO} contenant les nouvelles informations de la matière.
     *                       L'objet est validé avant traitement.
     * @return l'instance de la matière {@link Subject} mise à jour
     * @throws ResourceNotFoundException si aucune matière n'est trouvée avec l'identifiant fourni
     */
    @PutMapping(path = "/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody @Valid SubjectDTO updatedSubject) throws ResourceNotFoundException {
        return this.subjectService.updateSubject(id, updatedSubject);
    }

    /**
     * Supprime une matière en fonction de son identifiant.
     *
     * @param id l'identifiant de la matière à supprimer
     * @return une réponse HTTP avec le statut {@code 204 No Content} si la suppression a réussi
     * @throws ResourceNotFoundException si aucune matière n'est trouvée avec l'identifiant fourni
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) throws ResourceNotFoundException {
        this.subjectService.deleteSubject(id);

        return ResponseEntity.noContent().build();
    }

}
