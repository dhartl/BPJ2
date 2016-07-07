package at.c02.bpj.client.offer.management;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.service.EmployeeService;
import at.c02.bpj.client.service.OfferService;
import at.c02.bpj.client.test.MvvmFxGuiTest;
import at.c02.bpj.client.test.TestData;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.easydi.EasyDI;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;

@RunWith(MockitoJUnitRunner.class)
public class OfferManagementViewTest extends MvvmFxGuiTest {

	@Mock
	private OfferService offerService;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private CustomerService customerService;

	private Offer offer1;
	private Offer offer2;
	private Employee employee1;
	private Customer customer1;

	@Override
	public Class<? extends FxmlView<? extends ViewModel>> getViewClass() {
		return OfferManagementView.class;
	}

	@Override
	public void setupContext(EasyDI context) {
		offer1 = TestData.offer1();
		offer2 = TestData.offer2();
		employee1 = TestData.employee1();
		customer1 = TestData.customer1();

		Mockito.when(offerService.getOffer()).thenReturn(Lists.newArrayList(offer1, offer2));
		Mockito.when(employeeService.getEmployee()).thenReturn(Lists.newArrayList(employee1));
		Mockito.when(customerService.getCustomer()).thenReturn(Lists.newArrayList(customer1));

		context.bindInstance(OfferService.class, offerService);
		context.bindInstance(EmployeeService.class, employeeService);
		context.bindInstance(CustomerService.class, customerService);
	}

	@Test
	public void testInitialize() {
		TableView<Offer> offerTable = find("#offerTable");
		ComboBox<Employee> employeeField = find("#employeeField");
		ComboBox<Customer> customerField = find("#customerField");

		Assert.assertEquals(2, offerTable.getItems().size());
		Assert.assertEquals(1, employeeField.getItems().size());
		Assert.assertEquals(1, customerField.getItems().size());

	}

	// Suche Angebotsnummer 1
	@Test
	public void testSearchOfferNumber() {
		TableView<Offer> offerTable = find("#offerTable");
		click("#offerField").type("1");
		click("#searchButton");
		Assert.assertEquals(2, offerTable.getItems().size());
		Assert.assertEquals(offer1, offerTable.getItems().get(0));
	}

	// Suche Employee "Gmoser"

	@Test
	public void testSearchOfferEmployee() {
		TableView<Offer> offerTable = find("#offerTable");

		click("#employeeField").press(KeyCode.DOWN).press(KeyCode.ENTER);
		click("#searchButton");
		Assert.assertEquals(2, offerTable.getItems().size());
		Assert.assertEquals(employee1.getLastname(), offerTable.getItems().get(0).getEmployee().getLastname());
	}

	// Suche ohne Eingabe
	@Test
	public void testSearchOfferNoInput() {
		TableView<Offer> offerTable = find("#offerTable");
		click("#searchButton");
		Assert.assertTrue("Keine Suchparameter eingegeben", true);
	}

	// Suche Customer "A-GmbH"
	@Test
	public void testSearchOfferCustomer() {
		TableView<Offer> offerTable = find("#offerTable");

		click("#customerField").press(KeyCode.DOWN).press(KeyCode.ENTER);
		click("#searchButton");
		Assert.assertEquals(2, offerTable.getItems().size());
		Assert.assertEquals(customer1.getCompanyName(), offerTable.getItems().get(0).getCustomer().getCompanyName());

	}

}
