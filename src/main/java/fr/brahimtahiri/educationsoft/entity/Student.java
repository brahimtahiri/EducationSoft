package fr.brahimtahiri.educationsoft.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Student extends User {

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "classgroup_id")
    private Classgroup classgroup;

    @Builder
    public Student(String firstname, String lastname, String email, String address, String phoneNumber, String username, String password, Role role, Date dateOfBirth, Classgroup classgroup) {
        super(null, firstname, lastname, email, address, phoneNumber, username, password, role);
        this.dateOfBirth = dateOfBirth;
        this.classgroup = classgroup;
    }

}
