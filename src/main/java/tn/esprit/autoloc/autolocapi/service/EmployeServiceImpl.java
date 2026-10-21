package tn.esprit.autoloc.autolocapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;

@Service
public class EmployeServiceImpl extends GenericServiceImpl<Employe, Long> implements IEmployeService {

    public EmployeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Employe e, boolean isNew) {
        return e != null; // add your mandatory fields, e.g. && Utils.isNotEmpty(e.getNom())
    }

    @Override
    protected String getEntityName() { return "Employe"; }

    @Override
    protected Long getId(Employe e) { return e.getId(); }

    @Override
    protected void setFields(Employe source, Employe target) {
        BeanUtils.copyProperties(source, target, "id");
    }
}