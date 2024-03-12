package com.booking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "DataUser")
@DiscriminatorValue("USER")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT d FROM User d"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT d FROM User d WHERE d.login = :login")
})
public class User extends Account implements Serializable {

    public User() {
    }
    
    @NotNull
    @Pattern(regexp="^[0-9]{10}$",message="{constraint.string.incorrectNIP}")
    @Column(name = "nip", unique=true, nullable=false, length=10)
    private String nip;
    
    @OneToMany(mappedBy = "whoMadeReservation")
    private List<Reservation> reservations = new ArrayList<Reservation>();
    
    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
    
    public List<Reservation> getReservations() {
        return reservations;
    }
}
