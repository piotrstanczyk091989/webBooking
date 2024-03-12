package com.booking.model;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@TableGenerator(name = "BookingLabIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "BookingLab", initialValue=100)
@Entity
@Cacheable(false)
@Table(name = "BookingLab")@NamedQueries ({
      @NamedQuery(name="BookingLab.findBookingInLab", query="SELECT b FROM BookingLab b WHERE b.labReservation.number=:number ORDER BY b.stopBookingLab")
})
public class BookingLab extends AbstractEntity implements Serializable {

    public BookingLab() {
    }

    @Id
    @Column(name = "id", updatable = false) //NOT NULL i UNIQUE wynmikają z ograniczenia kliucza głównego
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BookingLabIdGen")
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="lastModification")
    private Date lastModification;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="startBookingLab")
    private Date startBookingLab;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="stopBookingLab")
    private Date stopBookingLab;
    
    @Column(name="confirmed")
    private boolean confirmed = true;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab labReservation;
 
    @NotNull
    @Size(max=200,message="{constraint.string.length.toolong}")
    @Column(name = "description", length = 200)
    private String description;
  
    @JoinColumn(nullable=false)
    @ManyToOne
    private Administrator whoMade;       

    @Override
    public Object getId() {
        return id;
    }    

    public Date getLastModification() {
            return lastModification;
    }

    @PrePersist
    @PreUpdate
    public void updateLastModification() {
            lastModification = new Date();
    }    
 
    public Date getStartBookingLab() {
        return startBookingLab;
    }

    public void setStartBookingLab(Date startBookingLab) {
        this.startBookingLab = startBookingLab;
    }

    public Date getStopBookingLab() {
        return stopBookingLab;
    }

    public void setStopBookingLab(Date stopBookingLab) {
        this.stopBookingLab = stopBookingLab;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    
    public Lab getLabReservation() {
        return labReservation;
    }

    public void setLabReservation(Lab labReservation) {
        this.labReservation = labReservation;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Administrator getWhoMade() {
        return whoMade;
    }

    public void setWhoMade(Administrator whoMade) {
        this.whoMade = whoMade;
    }

    @Override
    protected Object getBusinessKey() {
        return id;
    }
        
}
