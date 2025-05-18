package fr.brahimtahiri.educationsoft.service;

import fr.brahimtahiri.educationsoft.dto.TeacherDTO;
import fr.brahimtahiri.educationsoft.entity.Role;
import fr.brahimtahiri.educationsoft.entity.Teacher;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de gestion des enseignants.
 */
@Service
@AllArgsConstructor
public class TeacherService {

    private TeacherRepository teacherRepository;

    /**
     * Récupère la liste de tous les enseignants enregistrés en base de données.
     *
     * @return une liste contenant toutes les instances de {@link Teacher}
     */
    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    /**
     * Récupère un enseignant en fonction de son identifiant.
     *
     * @param id l'identifiant de l'enseignant à rechercher
     * @return l'instance de {@link Teacher} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucun enseignant n'est trouvé avec l'identifiant donné
     */
    public Teacher getTeacherById(Long id) throws ResourceNotFoundException {
        return this.teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Teacher with id %s not found.", id)));
    }

    /**
     * Crée et enregistre un nouvel enseignant à partir des données fournies.
     *
     * @param newTeacher un objet {@link TeacherDTO} contenant les informations nécessaires à la création de l'enseignant
     * @return l'instance de {@link Teacher} nouvellement créée et enregistrée
     */
    public Teacher createTeacher(TeacherDTO newTeacher) {
        return this.teacherRepository.save(Teacher.builder()
                .firstname(newTeacher.getFirstname())
                .lastname(newTeacher.getLastname())
                .email(newTeacher.getEmail())
                .address(newTeacher.getAddress())
                .phoneNumber(newTeacher.getPhoneNumber())
                .username(newTeacher.getUsername())
                .password(newTeacher.getPassword())
                .role(Role.TEACHER)
                .build());
    }

    /**
     * Met à jour un enseignant existant avec les nouvelles données fournies.
     *
     * @param id             l'identifiant de l'enseignant à mettre à jour
     * @param updatedTeacher un objet {@link TeacherDTO} contenant les nouvelles informations de l'enseignant
     * @return l'instance mise à jour de {@link Teacher}
     * @throws ResourceNotFoundException si aucun enseignant n'est trouvé avec l'identifiant fourni
     */
    public Teacher updateTeacher(Long id, TeacherDTO updatedTeacher) throws ResourceNotFoundException {
        Teacher currentTeacher = this.getTeacherById(id);

        currentTeacher.setLastname(updatedTeacher.getLastname());
        currentTeacher.setFirstname(updatedTeacher.getFirstname());
        currentTeacher.setEmail(updatedTeacher.getEmail());
        currentTeacher.setAddress(updatedTeacher.getAddress());
        currentTeacher.setPhoneNumber(updatedTeacher.getPhoneNumber());
        currentTeacher.setUsername(updatedTeacher.getUsername());
        currentTeacher.setPassword(updatedTeacher.getPassword());

        return this.teacherRepository.save(currentTeacher);
    }

    /**
     * Supprime un enseignant en fonction de son identifiant.
     *
     * @param id l'identifiant de l'enseignant à supprimer
     * @throws ResourceNotFoundException si aucun enseignant n'est trouvé avec l'identifiant fourni
     */
    public void deleteTeacher(Long id) throws ResourceNotFoundException {
        this.teacherRepository.delete(this.getTeacherById(id));
    }

}
