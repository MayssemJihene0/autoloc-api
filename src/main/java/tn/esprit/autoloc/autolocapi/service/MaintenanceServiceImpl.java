package tn.esprit.autoloc.autolocapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;

@Service
public class MaintenanceServiceImpl extends GenericServiceImpl<Maintenance, Long> implements IMaintenanceService {

    public MaintenanceServiceImpl(IMaintenanceRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Maintenance m, boolean isNew) {
        return m != null; // add your mandatory fields, e.g. && m.getVehicule() != null
    }

    @Override
    protected String getEntityName() { return "Maintenance"; }

    @Override
    protected Long getId(Maintenance m) { return m.getId(); }

    @Override
    protected void setFields(Maintenance source, Maintenance target) {
        BeanUtils.copyProperties(source, target, "id");
    }
}