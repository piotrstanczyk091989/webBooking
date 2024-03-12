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

@TableGenerator(name = "ReservationIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Reservation", initialValue=100)
@Entity
@Cacheable(false)
@Table(name = "Reservation")@NamedQueries ({
    @NamedQuery(name="Reservation.findUnApproved", query="SELECT r FROM Reservation r WHERE r.confirmed=false"),
    @NamedQuery(name="Reservation.findForUser", query="SELECT r FROM Reservation r WHERE r.whoMadeReservation.login=:login"),
    @NamedQuery(name="Reservation.findForEquipment", query="SELECT r FROM Reservation r WHERE r.equipmentReservation.id=:id AND r.confirmed=true"),
    @NamedQuery(name="Reservation.findAllReservtion", query="SELECT r FROM Reservation r ORDER BY r.equipmentReservation.lab, r.equipmentReservation.stationNumber")
})
public class Reservation extends AbstractEntity implements Serializable {

    public Reservation() {
    }

    @Id
    @Column(name = "id", updatable = false) 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ReservationIdGen")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="lastModification")
    private Date lastModification;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="startReservation")
    private Date startReservation;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="stopReservation")
    private Date stopReservation;
    
    @Column(name="confirmed")
    private boolean confirmed = true;
    
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipmentReservation;
    
    @JoinColumn(nullable=false)
    @ManyToOne
    private User whoMadeReservation;    

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
    
    public Date getStartReservation() {
        return startReservation;
    }

    public void setStartReservation(Date startReservation) {
        this.startReservation = startReservation;
    }

    public Date getStopReservation() {
        return stopReservation;
    }

    public void setStopReservation(Date stopReservation) {
        this.stopReservation = stopReservation;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    
    public User getWhoMade() {
        return whoMadeReservation;
    }

    public void setWhoMade(User whoMadeReservation) {
        this.whoMadeReservation = whoMadeReservation;
    }

    public Equipment getEquipmentReservation() {
        return equipmentReservation;
    }

    public void setEquipmentReservation(Equipment equipmentReservation) {
        this.equipmentReservation = equipmentReservation;
    }
    
    @Override
    protected Object getBusinessKey() {
        return id;
    }
        
}
