package com.booking.ejb.facades;

import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.exception.LabException;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Lab;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.eclipse.persistence.exceptions.DatabaseException;

@Stateless
@LocalBean
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class LabFacade extends AbstractFacade<Lab> {

    @PersistenceContext(unitName = "Booking_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LabFacade() {
        super(Lab.class);
    }
    
    @Override
    public void create(Lab entity) throws AppBaseException {
        try {
            super.create(entity);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() instanceof DatabaseException && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw LabException.createWithDbCheckConstraintKey(entity, ex);
            } else {
                throw ex;
            }
        }
    }
    
    @Override
    public void edit(Lab entity) throws AppBaseException {
        try {
            super.edit(entity);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() instanceof DatabaseException && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw LabException.createWithDbCheckConstraintKey(entity, ex);
            } else {
                throw ex;
            }
        }
    }
    
    @Override
    public void remove(Lab entity) throws AppBaseException {
        try {
            super.remove(entity);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() instanceof DatabaseException && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw LabException.removeWithDbCheckConstraintKey(entity, ex);
            } else {
                throw ex;
            }
        }
    }
}
