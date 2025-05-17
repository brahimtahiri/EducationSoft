package fr.brahimtahiri.educationsoft.controller;

import fr.brahimtahiri.educationsoft.dto.StudentDTO;
import fr.brahimtahiri.educationsoft.entity.Student;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/students")
@AllArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    /**
     * Récupère la liste de tous les étudiants enregistrés en base de données.
     *
     * @return une liste contenant tous les objets {@link Student}.
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return this.studentService.getAllStudents();
    }

    /**
     * Récupère un étudiant en fonction de son identifiant.
     *
     * @param id l'identifiant unique de l'étudiant à récupérer
     * @return l'objet {@link Student} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucun étudiant n'est trouvé avec l'identifiant donné
     */
    @GetMapping(path = "/{id}")
    public Student getStudentById(@PathVariable Long id) throws ResourceNotFoundException {
        return this.studentService.getStudentById(id);
    }

    /**
     * Crée un nouvel étudiant à partir des données fournies.
     *
     * @param studentDTO un objet {@link StudentDTO} contenant les informations nécessaires à la création de l'étudiant.
     *                   L'objet est validé avant traitement.
     * @return l'instance de l'étudiant {@link Student} nouvellement créée.
     * @throws ResourceNotFoundException si la classe associée à l'étudiant n'est pas trouvée
     */
    @PostMapping
    public Student createStudent(@RequestBody @Valid StudentDTO studentDTO) throws ResourceNotFoundException {
        return this.studentService.createStudent(studentDTO);
    }

    /**
     * Met à jour un étudiant existant avec les nouvelles données fournies.
     *
     * @param id         l'identifiant de l'étudiant à mettre à jour
     * @param studentDTO un objet {@link StudentDTO} contenant les nouvelles informations de l'étudiant.
     *                   L'objet est validé avant traitement.
     * @return l'instance de l'étudiant {@link Student} mise à jour
     * @throws ResourceNotFoundException si aucun étudiant n'est trouvé avec l'identifiant fourni ou si la classe associée est introuvable
     */
    @PutMapping(path = "/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO) throws ResourceNotFoundException {
        return this.studentService.updateStudent(id, studentDTO);
    }

    /**
     * Supprime un étudiant en fonction de son identifiant.
     *
     * @param id l'identifiant de l'étudiant à supprimer
     * @return une réponse HTTP avec le statut {@code 204 No Content} si la suppression a réussi
     * @throws ResourceNotFoundException si aucun étudiant n'est trouvé avec l'identifiant fourni
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) throws ResourceNotFoundException {
        this.studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Récupère la liste des étudiants appartenant à une classe donnée.
     *
     * @param classgroupId l'identifiant d'une de classe
     * @return une liste d'instances de {@link Student} appartenant à une classe spécifiée
     */
    @GetMapping(path = "/classgroup/{classgroupId}")
    public List<Student> getStudentsByClassGroupId(@PathVariable Long classgroupId) {
        return this.studentService.getStudentsByClassgroupId(classgroupId);
    }

}
