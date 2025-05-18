package fr.brahimtahiri.educationsoft.repository;

import fr.brahimtahiri.educationsoft.entity.Grading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradingRepository extends JpaRepository<Grading, Long> {
    List<Grading> findAllByStudentId(Long studentId);
}
