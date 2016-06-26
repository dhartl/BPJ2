package at.c02.bpj.client.api.model;

import java.sql.Date;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Offer {

	private ObjectProperty<Long> offerId = new SimpleObjectProperty<>();
	private ObjectProperty<Date> createdDt = new SimpleObjectProperty<>();
	private ObjectProperty<Date> completedDt = new SimpleObjectProperty<>();
	private StringProperty status = new SimpleStringProperty();
	private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
	private ObjectProperty<Employee> employee = new SimpleObjectProperty<>();
	private ObjectProperty<Date> insDt = new SimpleObjectProperty<>();
	private ObjectProperty<Long> insUserId = new SimpleObjectProperty<>();
	private ObjectProperty<Date> updDt = new SimpleObjectProperty<>();
	private ObjectProperty<Long> updUserId = new SimpleObjectProperty<>();
	private ObservableList<OfferPosition> offerPositions = FXCollections
			.observableArrayList(Offer::getPriceChangedProperties);
	
	public Offer() {
		
	}

	public final ObjectProperty<Long> offerIdProperty() {
		return this.offerId;
	}
	

	public final Long getOfferId() {
		return this.offerIdProperty().get();
	}
	

	public final void setOfferId(final Long offerId) {
		this.offerIdProperty().set(offerId);
	}
	

	public final ObjectProperty<Date> createdDtProperty() {
		return this.createdDt;
	}
	

	public final Date getCreatedDt() {
		return this.createdDtProperty().get();
	}
	

	public final void setCreatedDt(final Date createdDt) {
		this.createdDtProperty().set(createdDt);
	}
	

	public final ObjectProperty<Date> completedDtProperty() {
		return this.completedDt;
	}
	

	public final Date getCompletedDt() {
		return this.completedDtProperty().get();
	}
	

	public final void setCompletedDt(final Date completedDt) {
		this.completedDtProperty().set(completedDt);
	}
	

	public final StringProperty statusProperty() {
		return this.status;
	}
	

	public final String getStatus() {
		return this.statusProperty().get();
	}
	

	public final void setStatus(final String status) {
		this.statusProperty().set(status);
	}
	

	public final ObjectProperty<Customer> customerProperty() {
		return this.customer;
	}
	

	public final Customer getCustomer() {
		return this.customerProperty().get();
	}
	

	public final void setCustomer(final Customer customer) {
		this.customerProperty().set(customer);
	}
	

	public final ObjectProperty<Employee> employeeProperty() {
		return this.employee;
	}
	

	public final Employee getEmployee() {
		return this.employeeProperty().get();
	}
	

	public final void setEmployee(final Employee employee) {
		this.employeeProperty().set(employee);
	}
	

	public final ObjectProperty<Date> insDtProperty() {
		return this.insDt;
	}
	

	public final Date getInsDt() {
		return this.insDtProperty().get();
	}
	

	public final void setInsDt(final Date insDt) {
		this.insDtProperty().set(insDt);
	}
	

	public final ObjectProperty<Long> insUserIdProperty() {
		return this.insUserId;
	}
	

	public final Long getInsUserId() {
		return this.insUserIdProperty().get();
	}
	

	public final void setInsUserId(final Long insUserId) {
		this.insUserIdProperty().set(insUserId);
	}
	

	public final ObjectProperty<Date> updDtProperty() {
		return this.updDt;
	}
	

	public final Date getUpdDt() {
		return this.updDtProperty().get();
	}
	

	public final void setUpdDt(final Date updDt) {
		this.updDtProperty().set(updDt);
	}
	

	public final ObjectProperty<Long> updUserIdProperty() {
		return this.updUserId;
	}
	

	public final Long getUpdUserId() {
		return this.updUserIdProperty().get();
	}
	

	public final void setUpdUserId(final Long updUserId) {
		this.updUserIdProperty().set(updUserId);
	}

	public final ObservableList<OfferPosition> offerPositionsProperty() {
		return this.offerPositions;
	}

	public final void setOfferPositions(List<OfferPosition> offerPositions) {
		this.offerPositions.setAll(offerPositions);
	}

	public List<OfferPosition> getOfferPositions() {
		return offerPositions;
	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", createdDt=" + createdDt + ", completedDt=" + completedDt + ", status="
				+ status + ", customer=" + customer + ", employee=" + employee + ", insDt=" + insDt
				+ ", insUserId=" + insUserId + ", updDt=" + updDt + ", updUserId=" + updUserId + "]";
	}
	
	/**
	 * ListChangedListener wird auch aufgerufen, wenn sich Menge bzw. Preis in
	 * der OfferPosition ändert
	 * 
	 * @param offerPosition
	 * @return
	 */
	private static final Observable[] getPriceChangedProperties(OfferPosition offerPosition) {
		return new Observable[] { offerPosition.amountProperty(), offerPosition.priceProperty() };
	}
	
	
	
}
