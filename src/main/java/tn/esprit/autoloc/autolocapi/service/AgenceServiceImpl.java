package tn.esprit.autoloc.autolocapi.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
@Primary
@Service
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService {

    public AgenceServiceImpl(IAgenceRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return agence != null
                && Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getVille());
    }

    @Override
    public boolean hasVehicules(Long id) {
        return !findById(id).getVehicules().isEmpty();
    }

    @Override
    protected String getEntityName() { return "Agence"; }

    @Override
    protected Long getId(Agence agence) { return agence.getId(); }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setVille(source.getVille());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
    }
}