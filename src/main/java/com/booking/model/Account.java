package com.booking.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Account")
@SecondaryTable(name = "DataPersonal")
@TableGenerator(name = "AccountIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Account", initialValue=100)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "brand")
@DiscriminatorValue("ACCOUNT")
public class Account extends AbstractEntity implements Serializable {

    public Account() {
    }

    @Id
    @Column(name = "id", updatable = false) 
    
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountIdGen")
    private Long id;
    
    @NotNull(message="{constraint.notnull}")
    @Size(min=3,max=32,message="{constraint.string.length.notinrange}")
    @Column(name = "login", length = 32, nullable = false, unique = true, updatable = false)
    private String login;

    @NotNull(message="{constraint.notnull}")
    @Size(min=6,message="{constraint.string.length.tooshort}")
    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;
    
    @Column(name = "active", nullable = false)
    private boolean active;
    
    @Column(name="brand",updatable=false)
    private String brand;
   
    @NotNull(message="{constraint.notnull}")
    @Size(min=3,max=32,message="{constraint.string.length.notinrange}")
    @Column(name = "name", table = "DataPersonal", length = 32, nullable = false)
    private String name;

    @NotNull(message="{constraint.notnull}")
    @Size(min=3,max=32,message="{constraint.string.length.notinrange}")
    @Column(name = "surname", table = "DataPersonal", length = 32, nullable = false)
    private String surname;

    @NotNull(message="{constraint.notnull}")
    @Size(min=6,max=64,message="{constraint.string.length.notinrange}")
    @Pattern(regexp="^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$",message="{constraint.string.incorrectemail}")
    @Column(name = "email", table = "DataPersonal", length = 64, unique = true, nullable = false)
    private String email;
        
    @Size(max=12,message="{constraint.string.length.toolong}")
    @Column(name = "phone", table = "DataPersonal", length = 12, unique = true, nullable = true)
    private String phone;
    
    @Override
    public Long getId() {
        return id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    protected Object getBusinessKey() {
        return login;
    }
    
}
