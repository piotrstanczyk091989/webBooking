package com.booking.ejb.endpoints;

import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.facades.BookingLabFacade;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Account;
import com.booking.model.Administrator;
import com.booking.model.BookingLab;
import com.booking.web.utils.AccountUtils;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;


@Stateful
@LocalBean
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed({"Uzytkownik", "Administrator"})
public class BookingLabEndpoint extends AbstractEndpoint implements SessionSynchronization{
    
    @Inject
    private AccountEndpoint accountEndpoint;
    
    @Inject
    private BookingLabFacade bookingLabFacade;
    
    @RolesAllowed("Administrator") 
    public void createBookingLab(BookingLab bookingLab) throws AppBaseException {        
        bookingLabFacade.create(bookingLab);
    }
    
    @RolesAllowed("Administrator") 
    public void deleteBookingLab(BookingLab bookingLab) throws AppBaseException {
        bookingLabFacade.remove(bookingLab);
    }
    
    public List<BookingLab> downloadAllBookingLab() {
        return bookingLabFacade.findAll();
    }    
    
    public List<BookingLab> findBookingInLab(String number) {
        return bookingLabFacade.findBookingInLab(number);
    }
    
    
    public void whoAddBookingLab(BookingLab bookingLab) throws AppBaseException {
        
        Account myAccount = accountEndpoint.downloadMyAccount();
        
        if(!(AccountUtils.isAdministrator(myAccount))) {
            throw new IllegalArgumentException("Tylko administrator może rezerwowoać laboratorium");
        }
        Administrator myAccountAdministrator = (Administrator) myAccount;
        bookingLab.setWhoMade(myAccountAdministrator);
        
        myAccountAdministrator.getBooking().add(bookingLab);
        bookingLabFacade.create(bookingLab);
    }
    
}
