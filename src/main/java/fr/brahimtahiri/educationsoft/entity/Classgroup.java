package fr.brahimtahiri.educationsoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "classgroups")
@Getter
@Setter
public class Classgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 32)
    private String denomination;

    @OneToMany(mappedBy = "classgroup", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Student> students;

}
