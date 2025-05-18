package fr.brahimtahiri.educationsoft.service;

import fr.brahimtahiri.educationsoft.dto.GradingDTO;
import fr.brahimtahiri.educationsoft.entity.Grading;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.repository.GradingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service de gestion des notations.
 */
@Service
@AllArgsConstructor
public class GradingService {

    private final GradingRepository gradingRepository;

    private final SubjectService subjectService;

    private final StudentService studentService;

    /**
     * Récupère une notation en fonction de son identifiant.
     *
     * @param id l'identifiant unique de la notation à récupérer
     * @return l'objet {@link Grading} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucune notation n'est trouvée avec l'identifiant donné
     */
    public Grading getGradingById(Long id) throws ResourceNotFoundException {
        return this.gradingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Grading with id %s not found.", id)));
    }

    /**
     * Crée et enregistre une nouvelle notation à partir des données fournies.
     *
     * @param newGrading un objet {@link GradingDTO} contenant les informations nécessaires à la création de la notation
     * @return l'instance de {@link Grading} nouvellement créée et enregistrée
     * @throws ResourceNotFoundException si une erreur survient lors de la création
     */
    public Grading createGrading(GradingDTO newGrading) throws ResourceNotFoundException {
        return this.gradingRepository.save(Grading.builder()
                .type(newGrading.getType())
                .addedOn(new Date())
                .grade(newGrading.getGrade())
                .subject(this.subjectService.getSubjectById(newGrading.getSubjectId()))
                .student(this.studentService.getStudentById(newGrading.getStudentId()))
                .build());
    }

    /**
     * Met à jour une notation existante avec les nouvelles données fournies.
     *
     * @param id             l'identifiant de la notation à mettre à jour
     * @param updatedGrading un objet {@link GradingDTO} contenant les nouvelles informations de la notation
     * @return l'instance mise à jour de {@link Grading}
     * @throws ResourceNotFoundException si aucune notation n'est trouvée avec l'identifiant fourni
     */
    public Grading updateGrading(Long id, GradingDTO updatedGrading) throws ResourceNotFoundException {
        Grading currentGrading = this.getGradingById(id);

        currentGrading.setType(updatedGrading.getType());
        currentGrading.setGrade(updatedGrading.getGrade());
        currentGrading.setSubject(this.subjectService.getSubjectById(updatedGrading.getSubjectId()));
        currentGrading.setStudent(this.studentService.getStudentById(updatedGrading.getStudentId()));

        return this.gradingRepository.save(currentGrading);
    }

    /**
     * Supprime une notation en fonction de son identifiant.
     *
     * @param id l'identifiant de la notation à supprimer
     * @throws ResourceNotFoundException si aucune notation n'est trouvée avec l'identifiant fourni
     */
    public void deleteGrading(Long id) throws ResourceNotFoundException {
        this.gradingRepository.delete(this.getGradingById(id));
    }

    /**
     * Récupère toutes les notations associées à un étudiant spécifique.
     *
     * @param studentId l'identifiant unique de l'étudiant dont les notations doivent être récupérées
     * @return une liste contenant toutes les notations associées à l'étudiant spécifié
     */
    public List<Grading> getAllGradingByStudentId(Long studentId) {
        return this.gradingRepository.findAllByStudentId(studentId);
    }

}
