package fr.brahimtahiri.educationsoft.service;

import fr.brahimtahiri.educationsoft.dto.ClassgroupDTO;
import fr.brahimtahiri.educationsoft.entity.Classgroup;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.repository.ClassgroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassgroupService {

    private final ClassgroupRepository classgroupRepository;

    /**
     * Récupère la liste de toutes les classes enregistrées en base de données.
     *
     * @return une liste contenant toutes les instances de {@link Classgroup}
     */
    public List<Classgroup> getAllClassgroups() {
        return this.classgroupRepository.findAll();
    }

    /**
     * Récupère une classe en fonction de son identifiant.
     *
     * @param id l'identifiant de la classe à rechercher
     * @return l'instance de {@link Classgroup} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucune classe n'est trouvée avec l'identifiant donné
     */
    public Classgroup getClassgroupById(Long id) throws ResourceNotFoundException {
        return this.classgroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Classgroup with id %s not found.", id)));
    }

    /**
     * Crée et enregistre une nouvelle classe à partir des données fournies.
     *
     * @param newClassgroup un objet {@link ClassgroupDTO} contenant les informations nécessaires à la création de la classe
     * @return l'instance de {@link Classgroup} nouvellement créée et enregistrée
     */
    public Classgroup createClassgroup(ClassgroupDTO newClassgroup) {
        return this.classgroupRepository.save(Classgroup.builder().denomination(newClassgroup.getDenomination()).build());
    }

    /**
     * Met à jour une classe existante avec les nouvelles données fournies.
     *
     * @param id l'identifiant de la classe à mettre à jour
     * @param newClassgroup un objet {@link ClassgroupDTO} contenant les nouvelles informations de la classe
     * @return l'instance mise à jour de {@link Classgroup}
     * @throws ResourceNotFoundException si aucune classe n'est trouvée avec l'identifiant fourni
     */
    public Classgroup updateClassgroup(Long id, ClassgroupDTO newClassgroup) throws ResourceNotFoundException {
        Classgroup currentClassgroup = this.getClassgroupById(id);

        currentClassgroup.setDenomination(newClassgroup.getDenomination());

        return this.classgroupRepository.save(currentClassgroup);
    }

    /**
     * Supprime une classe en fonction de son identifiant.
     *
     * @param id l'identifiant de la classe à supprimer
     * @throws ResourceNotFoundException si aucune classe n'est trouvée avec l'identifiant fourni
     */
    public void deleteClassgroup(Long id) throws ResourceNotFoundException {
        this.classgroupRepository.delete(this.getClassgroupById(id));
    }

}
