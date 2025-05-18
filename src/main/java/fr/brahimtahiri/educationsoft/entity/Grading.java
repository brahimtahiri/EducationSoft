package fr.brahimtahiri.educationsoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "gradings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Grading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String type;

    @Column(name = "added_on", nullable = false)
    private Date addedOn;

    @Column(nullable = false)
    private Float grade;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
