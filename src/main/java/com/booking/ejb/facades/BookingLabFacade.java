package com.booking.ejb.facades;

import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.BookingLab;
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
public class BookingLabFacade extends AbstractFacade<BookingLab> {

    @PersistenceContext(unitName = "Booking_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookingLabFacade() {
        super(BookingLab.class);
    }    
          
    public List<BookingLab> findBookingInLab(String number) {
        TypedQuery<BookingLab> tq = em.createNamedQuery("BookingLab.findBookingInLab", BookingLab.class);
        tq.setParameter("number", number);
        return tq.getResultList();
    }
}
