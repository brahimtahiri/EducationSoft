package fr.brahimtahiri.educationsoft.controller;

import fr.brahimtahiri.educationsoft.dto.TeacherDTO;
import fr.brahimtahiri.educationsoft.entity.Teacher;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/teachers")
@AllArgsConstructor
public class TeacherRestController {

    private final TeacherService teacherService;

    /**
     * Récupère la liste de tous les enseignants enregistrés en base de données.
     *
     * @return une liste contenant tous les objets {@link Teacher}.
     */
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return this.teacherService.getAllTeachers();
    }

    /**
     * Récupère un enseignant en fonction de son identifiant.
     *
     * @param id l'identifiant unique de l'enseignant à récupérer
     * @return l'objet {@link Teacher} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucun enseignant n'est trouvé avec l'identifiant donné
     */
    @GetMapping(path = "/{id}")
    public Teacher getTeacherById(@PathVariable Long id) throws ResourceNotFoundException {
        return this.teacherService.getTeacherById(id);
    }

    /**
     * Crée un nouvel enseignant à partir des données fournies.
     *
     * @param newTeacher un objet {@link TeacherDTO} contenant les informations nécessaires à la création de l'enseignant.
     *                   L'objet est validé avant traitement.
     * @return l'instance de l'enseignant {@link Teacher} nouvellement créée.
     */
    @PostMapping
    public Teacher createTeacher(@RequestBody @Valid TeacherDTO newTeacher) {
        return this.teacherService.createTeacher(newTeacher);
    }

    /**
     * Met à jour un enseignant existant avec les nouvelles données fournies.
     *
     * @param id             l'identifiant de l'enseignant à mettre à jour
     * @param updatedTeacher un objet {@link TeacherDTO} contenant les nouvelles informations de l'enseignant.
     *                       L'objet est validé avant traitement.
     * @return l'instance de l'enseignant {@link Teacher} mise à jour
     * @throws ResourceNotFoundException si aucun enseignant n'est trouvé avec l'identifiant fourni
     */
    @PutMapping(path = "/{id}")
    public Teacher updateTeacher(@PathVariable Long id, @RequestBody @Valid TeacherDTO updatedTeacher) throws ResourceNotFoundException {
        return this.teacherService.updateTeacher(id, updatedTeacher);
    }

    /**
     * Supprime un enseignant en fonction de son identifiant.
     *
     * @param id l'identifiant de l'enseignant à supprimer
     * @return une réponse HTTP avec le statut {@code 204 No Content} si la suppression a réussi
     * @throws ResourceNotFoundException si aucun enseignant n'est trouvé avec l'identifiant fourni
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) throws ResourceNotFoundException {
        this.teacherService.deleteTeacher(id);

        return ResponseEntity.noContent().build();
    }

}
