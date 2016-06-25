package at.c02.bpj.client.offer;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.service.CustomerService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OfferChooseCustomerModel implements ViewModel {

    private ObservableList<Customer> customersList = FXCollections.observableArrayList();
    private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();

    public ObjectProperty<Customer> customerProperty() {
	return customer;
    }

    public ObservableList<Customer> customerListProperty() {
	return customersList;
    }

    public OfferChooseCustomerModel(CustomerService customerService) {
	customersList.addAll(customerService.getCustomer());
    }

}
