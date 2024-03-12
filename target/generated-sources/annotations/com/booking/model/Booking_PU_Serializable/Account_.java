package com.booking.model.Booking_PU_Serializable;

import com.booking.model.Account;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T18:02:52")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, String> phone;
    public static volatile SingularAttribute<Account, String> surname;
    public static volatile SingularAttribute<Account, String> name;
    public static volatile SingularAttribute<Account, Boolean> active;
    public static volatile SingularAttribute<Account, Long> id;
    public static volatile SingularAttribute<Account, String> login;
    public static volatile SingularAttribute<Account, Boolean> confirmed;
    public static volatile SingularAttribute<Account, String> brand;
    public static volatile SingularAttribute<Account, String> email;

}