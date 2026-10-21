package tn.esprit.autoloc.autolocapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;

@Service
public class ContratServiceImpl extends GenericServiceImpl<Contrat, Long> implements IContratService {

    public ContratServiceImpl(IContratRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Contrat c, boolean isNew) {
        return c != null; // add your mandatory fields
    }

    @Override
    protected String getEntityName() { return "Contrat"; }

    @Override
    protected Long getId(Contrat c) { return c.getId(); }

    @Override
    protected void setFields(Contrat source, Contrat target) {
        BeanUtils.copyProperties(source, target, "id", "paiements");
    }
}