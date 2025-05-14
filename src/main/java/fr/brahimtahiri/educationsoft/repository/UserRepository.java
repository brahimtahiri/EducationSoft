package fr.brahimtahiri.educationsoft.repository;

import fr.brahimtahiri.educationsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
