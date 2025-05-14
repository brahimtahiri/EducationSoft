package fr.brahimtahiri.educationsoft.repository;

import fr.brahimtahiri.educationsoft.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
