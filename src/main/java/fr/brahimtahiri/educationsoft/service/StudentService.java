package fr.brahimtahiri.educationsoft.service;

import fr.brahimtahiri.educationsoft.dto.StudentDTO;
import fr.brahimtahiri.educationsoft.entity.Classgroup;
import fr.brahimtahiri.educationsoft.entity.Role;
import fr.brahimtahiri.educationsoft.entity.Student;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de gestion des étudiants.
 */
@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final ClassgroupService classgroupService;

    /**
     * Récupère la liste de tous les étudiants enregistrés en base de données.
     *
     * @return une liste contenant toutes les instances de {@link Student}
     */
    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    /**
     * Récupère un étudiant en fonction de son identifiant.
     *
     * @param id l'identifiant de l'étudiant à rechercher
     * @return l'instance de {@link Student} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucun étudiant n'est trouvé avec l'identifiant donné
     */
    public Student getStudentById(Long id) throws ResourceNotFoundException {
        return this.studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id %s not found.", id)));
    }

    /**
     * Vérifie si un étudiant (DTO) est associé à une classe existante et retourne l'instance correspondante,
     * ou null si aucune classe n'est définie.
     * Cette méthode est utilisée lors de la création ou de la mise à jour d'un étudiant.
     *
     * @param newStudent un objet {@link StudentDTO} contenant les informations de l'étudiant
     * @return une instance de {@link Classgroup} si un groupe est associé, sinon null
     * @throws ResourceNotFoundException si la classe spécifiée n'existe pas
     */

    private Classgroup findClassgroupOrNull(StudentDTO newStudent) throws ResourceNotFoundException {
        if (newStudent.getClassgroupId() != null)
            return this.classgroupService.getClassgroupById(newStudent.getClassgroupId());
        else return null;
    }

    /**
     * Crée et enregistre un nouvel étudiant à partir des données fournies.
     *
     * @param newStudent un objet {@link StudentDTO} contenant les informations nécessaires à la création de l'étudiant
     * @return l'instance de {@link Student} nouvellement créée et enregistrée
     * @throws ResourceNotFoundException si le groupe de classe spécifié n'est pas trouvé
     */
    public Student createStudent(StudentDTO newStudent) throws ResourceNotFoundException {
        Classgroup classgroup = this.findClassgroupOrNull(newStudent);

        return this.studentRepository.save(Student.builder()
                .firstname(newStudent.getFirstname())
                .lastname(newStudent.getLastname())
                .email(newStudent.getEmail())
                .address(newStudent.getAddress())
                .phoneNumber(newStudent.getPhoneNumber())
                .username(newStudent.getUsername())
                .password(newStudent.getPassword())
                .role(Role.STUDENT)
                .dateOfBirth(newStudent.getDateOfBirth())
                .classgroup(classgroup)
                .build());
    }

    /**
     * Met à jour un étudiant existant avec les nouvelles données fournies.
     *
     * @param id             l'identifiant de l'étudiant à mettre à jour
     * @param updatedStudent un objet {@link StudentDTO} contenant les nouvelles informations de l'étudiant
     * @return l'instance mise à jour de {@link Student}
     * @throws ResourceNotFoundException si aucun étudiant n'est trouvé avec l'identifiant fourni ou si le groupe de classe spécifié est introuvable
     */
    public Student updateStudent(Long id, StudentDTO updatedStudent) throws ResourceNotFoundException {
        Student currentStudent = this.getStudentById(id);
        Classgroup classgroup = this.findClassgroupOrNull(updatedStudent);

        currentStudent.setFirstname(updatedStudent.getFirstname());
        currentStudent.setLastname(updatedStudent.getLastname());
        currentStudent.setEmail(updatedStudent.getEmail());
        currentStudent.setAddress(updatedStudent.getAddress());
        currentStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
        currentStudent.setUsername(updatedStudent.getUsername());
        currentStudent.setPassword(updatedStudent.getPassword());
        currentStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        currentStudent.setClassgroup(classgroup);

        return this.studentRepository.save(currentStudent);
    }

    /**
     * Supprime un étudiant en fonction de son identifiant.
     *
     * @param id l'identifiant de l'étudiant à supprimer
     * @throws ResourceNotFoundException si aucun étudiant n'est trouvé avec l'identifiant fourni
     */
    public void deleteStudent(Long id) throws ResourceNotFoundException {
        this.studentRepository.delete(this.getStudentById(id));
    }

    /**
     * Récupère la liste des étudiants qui ne sont associés à aucune classe.
     *
     * @return une liste contenant tous les étudiants dont la classe est null
     */
    public List<Student> getStudentsWithoutClassgroup() {
        return this.studentRepository.findByClassgroupIsNull();
    }

    /**
     * Récupère la liste des étudiants appartenant à un groupe de classe donné.
     *
     * @param classgroupId l'identifiant du groupe de classe
     * @return une liste d'instances de {@link Student} appartenant au groupe de classe spécifié
     */
    public List<Student> getStudentsByClassgroupId(Long classgroupId) {
        return this.studentRepository.findByClassgroupId(classgroupId);
    }

}
