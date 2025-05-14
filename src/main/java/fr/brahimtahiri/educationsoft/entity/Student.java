package fr.brahimtahiri.educationsoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User {

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "classgroup_id")
    private Classgroup classgroup;

}
