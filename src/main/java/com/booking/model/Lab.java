package com.booking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@TableGenerator(name = "LabIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Lab", initialValue = 100)
@Entity
@Table(name = "Lab")
@NamedQueries({
    @NamedQuery(name = "Lab.findAll", query = "SELECT l FROM Lab l"),
    @NamedQuery(name = "Lab.findByNumber", query = "SELECT l FROM Lab l WHERE l.number = :number")    
})
public class Lab extends AbstractEntity implements Serializable{

    public Lab() {
    }
            
    @Id
    @Column(name = "id", updatable = false) 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "LabIdGen")
    private Long id;
            
    @NotNull
    @Size(min = 1, max = 6, message = "{constraint.string.length.notinrange}")
    @Column(name = "number", length = 6, nullable = false, unique = true)
    private String number;
    
    @NotNull
    @Size(min = 1, max = 5, message = "{constraint.string.length.notinrange}")
    @Pattern(regexp="[0-9]{1,2}",message = "{constraint.string.incorrectfloor}")
    @Column(name = "floor", length = 3, nullable = false)
    private String floor;
    
    @OneToMany(mappedBy = "lab", fetch= FetchType.EAGER)
    private List<Equipment> equipmentsInLab = new ArrayList<Equipment>();

    @OneToMany(mappedBy = "labReservation", cascade={CascadeType.PERSIST, CascadeType.REMOVE} )   
    private List<BookingLab> reservations = new ArrayList();

    @Override
    public Long getId() {
        return id;
    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
        
    public List<Equipment> getEquipmentsInLab() {
        return equipmentsInLab;
    }

    public void setEquipmentsInLab(List<Equipment> equipmentsInLab) {
        this.equipmentsInLab = equipmentsInLab;
    }

    public List<BookingLab> getReservations() {
        return reservations;
    }

    public void setReservations(List<BookingLab> reservations) {
        this.reservations = reservations;
    }
    
    @Override
    protected Object getBusinessKey() {
        return number;
    }
    
}
