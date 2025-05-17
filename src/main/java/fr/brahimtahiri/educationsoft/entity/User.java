package fr.brahimtahiri.educationsoft.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String firstname;

    @Column(nullable = false, length = 64)
    private String lastname;

    @Column(nullable = false, length = 64)
    private String email;

    @Column(nullable = false, length = 64)
    private String address;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 34)
    private String username;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void setPassword(String password) {
        System.out.printf("%n%s : Le mot de passe reste en clair car le module d'authentification est indisponible.%n", this.username);
        this.password = password;
    }

}
