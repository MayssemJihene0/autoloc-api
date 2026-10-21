package tn.esprit.autoloc.autolocapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;

@Service
public class EquipementServiceImpl extends GenericServiceImpl<Equipement, Long> implements IEquipementService {

    public EquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Equipement e, boolean isNew) {
        return e != null; // add your mandatory fields, e.g. && Utils.isNotEmpty(e.getNom())
    }

    @Override
    protected String getEntityName() { return "Equipement"; }

    @Override
    protected Long getId(Equipement e) { return e.getId(); }

    @Override
    protected void setFields(Equipement source, Equipement target) {
        BeanUtils.copyProperties(source, target, "id", "vehicules");
    }
}