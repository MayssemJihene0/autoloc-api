package tn.esprit.autoloc.autolocapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements IClientService {

    public ClientServiceImpl(IClientRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Client c, boolean isNew) {
        return c != null; // add your mandatory fields, e.g. && Utils.isNotEmpty(c.getNom())
    }

    @Override
    protected String getEntityName() { return "Client"; }

    @Override
    protected Long getId(Client c) { return c.getId(); }

    @Override
    protected void setFields(Client source, Client target) {
        BeanUtils.copyProperties(source, target, "id", "reservations");
    }
}