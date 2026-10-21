package tn.esprit.autoloc.autolocapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.math.BigDecimal;
import java.net.InterfaceAddress;
import java.util.Comparator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class AgenceTests {
    @Autowired
    private AgenceRepositoryMock agenceRepository;
    @Test
    public void addAgence() {
        Vehicule v1 = Vehicule.builder()
                .immatriculation("785414TU96")
                .marque("Isuzu")
                .modele("DMax")
                .statut(StatutVehicule.MAINTENANCE)
                .categorie(CategorieVehicule.SUV)
                .prixParJour(new BigDecimal("100"))
                .build();

        Vehicule v2 = Vehicule.builder()
                .immatriculation("785414TU95")
                .marque("Toyota")
                .modele("Yaris")
                .statut(StatutVehicule.DISPONIBLE)
                .categorie(CategorieVehicule.UTILITAIRE)
                .prixParJour(new BigDecimal("80"))
                .build();

        Agence agence = Agence.builder()
                .nom("Agence ariana")
                .adresse("1 Rue Hedi")
                .telephone("71585874")
                .ville("Tunis")
                .vehicules(Set.of(v1, v2))
                .build();

        v1.setAgence(agence);
        v2.setAgence(agence);

        agenceRepository.save(agence);
    }
    @Test
    public void loadAgence() {
        StringBuilder sb = new StringBuilder();

        for (Agence a : agenceRepository.findAll()) {
            sb.append("\n")
                    .append(a.getId()).append(" | ").append(a.getNom()).append("\n")
                    .append("Vehicules Count : ").append(a.getVehicules().size()).append("\n");

            for (Vehicule v : a.getVehicules()) {
                sb.append("=== ")
                        .append(v.getId()).append("|").append(v.getImmatriculation())
                        .append("\n");
            }
        }
        fail(sb.toString());
    }

    }
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {

}

