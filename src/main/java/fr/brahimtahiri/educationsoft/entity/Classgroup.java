package fr.brahimtahiri.educationsoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "classgroups")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Classgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 32)
    private String denomination;

    @OneToMany(mappedBy = "classgroup", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Student> students;

}
