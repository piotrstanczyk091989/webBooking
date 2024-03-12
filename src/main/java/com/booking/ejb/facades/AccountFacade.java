package com.booking.ejb.facades;

import com.booking.ejb.exception.AccountException;
import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Account;
import com.booking.model.Booking_PU.Account_;
import java.sql.SQLIntegrityConstraintViolationException;
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
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "Booking_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }

    @Override
    public void create(Account entity) throws AppBaseException {
        try {
            super.create(entity);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() instanceof DatabaseException && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw AccountException.createWithDbCheckConstraintKey(entity, ex);
            } else {
                throw ex;
            }
        }
    }

    public Account findLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);
        query = query.where(cb.equal(from.get(Account_.login), login));
        TypedQuery<Account> tq = em.createQuery(query);

        return tq.getSingleResult();
    }

    public List<Account> matchAccount(String loginPattern, String namePattern, String surnamePattern, String emailPattern) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);
        Predicate criteria = cb.conjunction();
        if (null != loginPattern && !(loginPattern.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(Account_.login), '%' + loginPattern + '%'));
        }
        if (null != namePattern && !(namePattern.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(Account_.name), '%' + namePattern + '%'));
        }
        if (null != surnamePattern && !(surnamePattern.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(Account_.surname), '%' + surnamePattern + '%'));
        }
        if (null != emailPattern && !(emailPattern.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(Account_.email), '%' + emailPattern + '%'));
        }

        query = query.where(criteria);
        TypedQuery<Account> tq = em.createQuery(query);
        return tq.getResultList();
    }

}
