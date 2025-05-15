package fr.brahimtahiri.educationsoft.controller;

import fr.brahimtahiri.educationsoft.dto.ClassgroupDTO;
import fr.brahimtahiri.educationsoft.entity.Classgroup;
import fr.brahimtahiri.educationsoft.exception.ResourceNotFoundException;
import fr.brahimtahiri.educationsoft.service.ClassgroupService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/classgroups")
@AllArgsConstructor
public class ClassgroupRestController {

    private final ClassgroupService classgroupService;

    /**
     * Récupère la liste de toutes les classes.
     *
     * @return une liste contenant tous les objets {@link Classgroup}.
     */
    @GetMapping
    public List<Classgroup> getAllClassgroups() {
        return this.classgroupService.getAllClassgroups();
    }

    /**
     * Récupère une classe en fonction de son identifiant.
     *
     * @param id l'identifiant unique d'une classe à récupérer
     * @return l'objet {@link Classgroup} correspondant à l'identifiant fourni
     * @throws ResourceNotFoundException si aucune classe n'est trouvé avec l'identifiant donné
     */
    @GetMapping(path = "/{id}")
    public Classgroup getClassgroupById(@PathVariable Long id) throws ResourceNotFoundException {
        return this.classgroupService.getClassgroupById(id);
    }

    /**
     * Crée une nouvelle classe à partir des données fournies.
     *
     * @param newClassgroup un objet {@link ClassgroupDTO} contenant les informations nécessaires à la création de la classe.
     * L'objet est validé avant traitement.
     * @return l'instance de la classe {@link Classgroup} nouvellement créée.
     */
    @PostMapping
    public Classgroup createClassgroup(@RequestBody @Valid ClassgroupDTO newClassgroup) {
        return this.classgroupService.createClassgroup(newClassgroup);
    }

    /**
     * Met à jour une classe existante avec les nouvelles données fournies.
     *
     * @param id l'identifiant de la classe à mettre à jour
     * @param newClassgroup un objet {@link ClassgroupDTO} contenant les nouvelles informations de la classe.
     *                      L'objet est validé avant traitement.
     * @return l'instance de la classe {@link Classgroup} mise à jour
     * @throws ResourceNotFoundException si aucune classe n'est trouvée avec l'identifiant fourni
     */
    @PutMapping(path = "/{id}")
    public Classgroup updateClassgroup(@PathVariable Long id, @RequestBody @Valid ClassgroupDTO newClassgroup) throws ResourceNotFoundException {
        return this.classgroupService.updateClassgroup(id, newClassgroup);
    }

    /**
     * Supprime une classe en fonction de son identifiant.
     *
     * @param id l'identifiant de la classe à supprimer
     * @return une réponse HTTP avec le statut {@code 204 No Content} si la suppression a réussi
     * @throws ResourceNotFoundException si aucune classe n'est trouvée avec l'identifiant fourni
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteClassgroup(@PathVariable Long id) throws ResourceNotFoundException {
        this.classgroupService.deleteClassgroup(id);

        return ResponseEntity.noContent().build();
    }

}
