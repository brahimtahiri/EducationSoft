package fr.brahimtahiri.educationsoft.service;

import fr.brahimtahiri.educationsoft.dto.SubjectDTO;
import fr.brahimtahiri.educationsoft.entity.Subject;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de gestion des matières (subjects).
 */
@Service
@AllArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    /**
     * Récupère la liste de toutes les matières enregistrées en base de données.
     *
     * @return une liste contenant toutes les instances de {@link Subject}
     */
    public List<Subject> getAllSubjects() {
        return this.subjectRepository.findAll();
    }

    /**
     * Récupère une matière en fonction de son identifiant.
     *
     * @param id l'identifiant de la matière à rechercher
     * @return l'instance de {@link Subject} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucune matière n'est trouvée avec l'identifiant donné
     */
    public Subject getSubjectById(Long id) throws ResourceNotFoundException {
        return this.subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Subject with id %s not found.", id)));
    }

    /**
     * Crée et enregistre une nouvelle matière à partir des données fournies.
     *
     * @param newSubject un objet {@link SubjectDTO} contenant les informations nécessaires à la création de la matière
     * @return l'instance de {@link Subject} nouvellement créée et enregistrée
     */
    public Subject createSubject(SubjectDTO newSubject) {
        return this.subjectRepository.save(Subject.builder().denomination(newSubject.getDenomination()).build());
    }

    /**
     * Met à jour une matière existante avec les nouvelles données fournies.
     *
     * @param id             l'identifiant de la matière à mettre à jour
     * @param updatedSubject un objet {@link SubjectDTO} contenant les nouvelles informations de la matière
     * @return l'instance mise à jour de {@link Subject}
     * @throws ResourceNotFoundException si aucune matière n'est trouvée avec l'identifiant fourni
     */
    public Subject updateSubject(Long id, SubjectDTO updatedSubject) throws ResourceNotFoundException {
        Subject currentSubject = this.getSubjectById(id);

        currentSubject.setDenomination(updatedSubject.getDenomination());

        return this.subjectRepository.save(currentSubject);
    }

    /**
     * Supprime une matière en fonction de son identifiant.
     *
     * @param id l'identifiant de la matière à supprimer
     * @throws ResourceNotFoundException si aucune matière n'est trouvée avec l'identifiant fourni
     */
    public void deleteSubject(Long id) throws ResourceNotFoundException {
        this.subjectRepository.delete(this.getSubjectById(id));
    }

}
