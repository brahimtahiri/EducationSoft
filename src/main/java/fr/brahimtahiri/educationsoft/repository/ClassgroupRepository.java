package fr.brahimtahiri.educationsoft.repository;

import fr.brahimtahiri.educationsoft.entity.Classgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassgroupRepository extends JpaRepository<Classgroup, Long> {
}
