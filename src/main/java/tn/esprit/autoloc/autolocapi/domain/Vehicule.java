package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.util.List;
@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    private String modele;

    private Integer annee;

    private String couleur;

    private Integer kilometrage;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prixParJour;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatutVehicule statut;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CategorieVehicule categorie;
    @ManyToOne
    private Agence agence;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

    @ManyToMany
    private List<Equipement> equipements;
}
