package com.booking.ejb.facades;

import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Reservation;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ReservationFacade extends AbstractFacade<Reservation> {

    @PersistenceContext(unitName = "Booking_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }
    
    public List<Reservation> findReservationForUser(String login) {
        TypedQuery<Reservation> tq = em.createNamedQuery("Reservation.findForUser", Reservation.class);
        tq.setParameter("login", login);
        return tq.getResultList();
    }
    
    public List<Reservation> findReservtionForEquipment(Long id) {
        TypedQuery<Reservation> tq = em.createNamedQuery("Reservation.findForEquipment", Reservation.class);
        tq.setParameter("id", id);
        return tq.getResultList();
    }
    
     public List<Reservation> findAllReservtion() {
        TypedQuery<Reservation> tq = em.createNamedQuery("Reservation.findAllReservtion", Reservation.class);        
        return tq.getResultList();
    }
     
    
}
