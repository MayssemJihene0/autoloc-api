package tn.esprit.autoloc.autolocapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    @Autowired
    private IAgenceService agenceService;

    private void addAgence(CrudRepository<Agence, Long> repository) {
        int suffix = (int) System.currentTimeMillis();

        Vehicule v1 = Vehicule.builder()
                .immatriculation("TN-" + suffix + "-1")
                .marque("Isuzu")
                .modele("DMax")
                .statut(StatutVehicule.MAINTENANCE)
                .categorie(CategorieVehicule.SUV)
                .prixParJour(new BigDecimal("100"))
                .build();

        Vehicule v2 = Vehicule.builder()
                .immatriculation("TN-" + suffix + "-2")
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

        repository.save(agence);
    }

    @Test
    public void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    public void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    private void loadAgence(CrudRepository<Agence, Long> repository, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("Repository type : ").append(type).append("\n");

        for (Agence a : repository.findAll()) {
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

    @Test
    public void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "basic (CrudRepository)");
    }

    @Test
    public void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "full (JpaRepository)");
    }

    @Test
    public void loadSortedAgences() {
        List<Agence> agences = fullAgenceRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        StringBuilder sb = new StringBuilder();
        for (Agence a : agences) {
            sb.append(a.getId()).append(" | ").append(a.getNom())
                    .append(" | ").append(a.getVille()).append("\n");
        }
        fail(sb.toString());
    }

    @Test
    public void loadPagedAgences() {
        Page<Agence> page = fullAgenceRepository.findAll(
                PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "id")));

        StringBuilder sb = new StringBuilder();
        sb.append("Total pages : ").append(page.getTotalPages()).append("\n");
        sb.append("Current page : ").append(page.getNumber()).append("\n");
        for (Agence a : page.getContent()) {
            sb.append(a.getId()).append(" | ").append(a.getNom())
                    .append(" | ").append(a.getVille()).append("\n");
        }
        fail(sb.toString());
    }

    @Test
    public void createAgence() {
        Agence agence = Agence.builder()
                .nom("Agence service")
                .adresse("2 Rue Test")
                .telephone("71000000")
                .ville("Sfax")
                .build();

        Agence saved = agenceService.create(agence);

        assertNotNull(saved.getId());
    }
}

@Repository
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}