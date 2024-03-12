package com.booking.ejb.facades;

import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import java.util.ArrayList;
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
public class EquipmentFacade extends AbstractFacade<Equipment> {

    @PersistenceContext(unitName = "Booking_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipmentFacade() {
        super(Equipment.class);
    }
    
    public List<Equipment> findEquipmentFromLab(String number) {
        TypedQuery<Equipment> tq = em.createNamedQuery("Equipment.findEquipmentFromLab", Equipment.class);
        tq.setParameter("number", number);
        return tq.getResultList();
    }
    
    public List<EquipmentComponents> findEquipmentComponentFromEquipment(Long id) {
        TypedQuery<Equipment> tq = em.createNamedQuery("Equipment.findEquipmentById", Equipment.class);
        tq.setParameter("id", id);
        Equipment equipment = tq.getSingleResult();
        List ec = new ArrayList<EquipmentComponents>();        
        ec = equipment.getEquipmentComponents();        
        return ec;
    }   

    public void addEquipmentComponent(EquipmentComponents equipmentComponents) {
        em.merge(equipmentComponents);
    }

    public void removeEquipmentComponent(EquipmentComponents equipmentComponents) {
        em.merge(equipmentComponents);
    }

    
}
