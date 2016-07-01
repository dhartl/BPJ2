package at.c02.bpj.server.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer extends ModLogEntity<Long> {
    private static final long serialVersionUID = -2083968170968404079L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offerId")
    private Long offerId;
    @Column(name = "createdDt")
    private Date createdDt;
    @Column(name = "completedDt")
    private Date completedDt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OfferStatus status;
	@ManyToOne(optional = false)
    @JoinColumn(name = "customerId")
    private Customer customer;
	@ManyToOne(optional = false)
    @JoinColumn(name = "employeeId")
    private Employee employee;
	@OneToMany(mappedBy = "offer", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<OfferPosition> offerPositions = new LinkedHashSet<>();

    @Override
    public Long getId() {
	return getOfferId();
    }

    public Long getOfferId() {
	return offerId;
    }

    public void setOfferId(Long offerId) {
	this.offerId = offerId;
    }

    public Date getCreatedDt() {
	return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
	this.createdDt = createdDt;
    }

    public Date getCompletedDt() {
	return completedDt;
    }

    public void setCompletedDt(Date completedDt) {
	this.completedDt = completedDt;
    }

    public OfferStatus getStatus() {
	return status;
    }

    public void setStatus(OfferStatus status) {
	this.status = status;
    }

    public Customer getCustomer() {
	return customer;
    }

    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

    public Employee getEmployee() {
	return employee;
    }

    public void setEmployee(Employee employee) {
	this.employee = employee;
    }

    public Set<OfferPosition> getOfferPositions() {
	return offerPositions;
    }

    public void setOfferPositions(Set<OfferPosition> offerPositions) {
	this.offerPositions = offerPositions;
    }
}