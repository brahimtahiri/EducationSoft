package fr.brahimtahiri.educationsoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Grading> gradings;

    @Builder
    public Student(String firstname, String lastname, String email, String address, String phoneNumber, String username, String password, Role role, Date dateOfBirth, Classgroup classgroup) {
        super(null, firstname, lastname, email, address, phoneNumber, username, password, role);
        this.dateOfBirth = dateOfBirth;
        this.classgroup = classgroup;
    }

}
