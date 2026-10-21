package tn.esprit.autoloc.autolocapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation, Long> implements IReservationService {

    public ReservationServiceImpl(IReservationRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Reservation r, boolean isNew) {
        return r != null; // add your mandatory fields, e.g. && r.getVehicule() != null && r.getClient() != null
    }

    @Override
    protected String getEntityName() { return "Reservation"; }

    @Override
    protected Long getId(Reservation r) { return r.getId(); }

    @Override
    protected void setFields(Reservation source, Reservation target) {
        BeanUtils.copyProperties(source, target, "id");
    }
}