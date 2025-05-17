package fr.brahimtahiri.educationsoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@Getter
@Setter
public class Teacher extends User {

    @Builder
    public Teacher(String firstname, String lastname, String email, String address, String phoneNumber, String username, String password, Role role) {
        super(null, firstname, lastname, email, address, phoneNumber, username, password, role);
    }

}
