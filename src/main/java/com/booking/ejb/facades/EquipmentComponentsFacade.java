package com.booking.ejb.facades;

import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.exception.EquipmentComponentsException;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Booking_PU.EquipmentComponents_;
import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.exceptions.DatabaseException;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class EquipmentComponentsFacade extends AbstractFacade<EquipmentComponents> {

    @PersistenceContext(unitName = "Booking_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipmentComponentsFacade() {
        super(EquipmentComponents.class);
    }
    
    @Override
    public void create(EquipmentComponents entity) throws AppBaseException {
        try {
            super.create(entity);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() instanceof DatabaseException && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw EquipmentComponentsException.createWithDbCheckConstraintKey(entity, ex);
            } else {
                throw ex;
            }
        }
    }
    
    @Override
    public void edit(EquipmentComponents entity) throws AppBaseException {
        try {
            super.edit(entity);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() instanceof DatabaseException && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw EquipmentComponentsException.createWithDbCheckConstraintKey(entity, ex);
            } else {
                throw ex;
            }
        }
    }
    
     public List<EquipmentComponents> matchComponent(String typeComponentPattern, String nameComponentPattern) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EquipmentComponents> query = cb.createQuery(EquipmentComponents.class);
        Root<EquipmentComponents> from = query.from(EquipmentComponents.class);
        query = query.select(from);
        Predicate criteria = cb.conjunction();
        if (null != typeComponentPattern && !(typeComponentPattern.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(EquipmentComponents_.typeComponent), '%' + typeComponentPattern + '%'));
        }
        if (null != nameComponentPattern && !(nameComponentPattern.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(EquipmentComponents_.nameComponent), '%' + nameComponentPattern + '%'));
        }
        
        query = query.where(criteria);
        TypedQuery<EquipmentComponents> tq = em.createQuery(query);
        return tq.getResultList();
    }

    public void addEquipmentComponentToEquipmeent(Equipment equipment) {
        em.merge(equipment);
    }

    public List<Equipment> findEquipmentWithComponent(Long id) {
        TypedQuery<EquipmentComponents> tq = em.createNamedQuery("EquipmentComponents.findEquipmentComponentById", EquipmentComponents.class);
        tq.setParameter("id", id);
        EquipmentComponents equipmentComponents = tq.getSingleResult();
        List<Equipment> equipments = new ArrayList<Equipment>();        
        equipments = equipmentComponents.getEquipments();        
        return equipments;
    }
    
}
