package at.c02.bpj.client.api.model;

import java.util.Date;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Offer {

	private ObjectProperty<Long> offerId = new SimpleObjectProperty<>();
	private ObjectProperty<Date> createdDt = new SimpleObjectProperty<>();
	private ObjectProperty<Date> completedDt = new SimpleObjectProperty<>();
	private ObjectProperty<OfferStatus> status = new SimpleObjectProperty<>();
	private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
	private ObjectProperty<Employee> employee = new SimpleObjectProperty<>();
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

	public final ObjectProperty<OfferStatus> statusProperty() {
		return this.status;
	}

	public final OfferStatus getStatus() {
		return this.statusProperty().get();
	}

	public final void setStatus(final OfferStatus status) {
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
				+ status + ", customer=" + customer + ", employee=" + employee + "]";
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
