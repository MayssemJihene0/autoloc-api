package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {

    public VehiculeServiceImpl(IVehiculeRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Vehicule v, boolean isNew) {
        return v != null
                && Utils.isNotEmpty(v.getImmatriculation())
                && Utils.isNotEmpty(v.getMarque())
                && Utils.isNotEmpty(v.getModele())
                && v.getPrixParJour() != null
                && v.getStatut() != null
                && v.getCategorie() != null;
    }

    @Override
    protected String getEntityName() { return "Vehicule"; }

    @Override
    protected Long getId(Vehicule v) { return v.getId(); }

    @Override
    protected void setFields(Vehicule source, Vehicule target) {
        target.setImmatriculation(source.getImmatriculation());
        target.setMarque(source.getMarque());
        target.setModele(source.getModele());
        target.setAnnee(source.getAnnee());
        target.setCouleur(source.getCouleur());
        target.setKilometrage(source.getKilometrage());
        target.setPrixParJour(source.getPrixParJour());
        target.setStatut(source.getStatut());
        target.setCategorie(source.getCategorie());
        target.setAgence(source.getAgence());
    }
}