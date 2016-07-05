package at.c02.bpj.client.offer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.service.EmployeeService;
import at.c02.bpj.client.test.MvvmFxGuiTest;
import at.c02.bpj.client.test.TestData;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.easydi.EasyDI;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

@RunWith(MockitoJUnitRunner.class)
public class OfferChooseCustomerTest extends MvvmFxGuiTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private CustomerService customerService;

    private Customer customer1;

    @Override
    public Class<? extends FxmlView<? extends ViewModel>> getViewClass() {
	return OfferChooseCustomerView.class;
    }

    @Override
    public void setupContext(EasyDI context) {
	customer1 = TestData.customer1();

	Mockito.when(customerService.getCustomer()).thenReturn(Lists.newArrayList(customer1));

	context.bindInstance(CustomerService.class, customerService);
    }

    @Test
    public void testInitialize() {
	ComboBox<Customer> cbxCustomer = find("#cbxCustomer");
	Assert.assertEquals(1, cbxCustomer.getItems().size());

    }

    // #005 Kunde A-GmbH wird über Combobox ausgewählt -> Details werden
    // angezeigt.
    @Test
    public void testShowDetailsOfChoosenCustomer() {
	ComboBox<Customer> cbxCustomer = find("#cbxCustomer");
	Label lblCustomerName = find("#lblCustomerName");
	Label lblCustomerCity = find("#lblCustomerCity");
	Label lblCustomerStreet = find("#lblCustomerStreet");
	Label lblCustomerHouseNr = find("#lblCustomerHouseNr");
	Label lblCustomerPostCode = find("#lblCustomerPostCode");
	Label lblCustomerContactPartnerFN = find("#lblCustomerContactPartnerFN");
	Label lblCustomerContactPartnerLN = find("#lblCustomerContactPartnerLN");

	click("#cbxCustomer").press(KeyCode.DOWN).press(KeyCode.ENTER);
	Assert.assertEquals("A-GmbH", lblCustomerName.getText());
	Assert.assertEquals("Graz", lblCustomerCity.getText());
	Assert.assertEquals("A-Straße", lblCustomerStreet.getText());
	Assert.assertEquals("10", lblCustomerHouseNr.getText());
	Assert.assertEquals("8010", lblCustomerPostCode.getText());
	Assert.assertEquals("Max", lblCustomerContactPartnerFN.getText());
	Assert.assertEquals("Mustermann", lblCustomerContactPartnerLN.getText());

    }

    // #006 Kunde xyz wird über Combobox nicht gefunden -> Kundenmanagement über
    // Button erreichbar.
    @Test
    public void testOpenOfferCreateView() {
	Button btnCreateNewCustomer = find("#btnCreateNewCustomer");
	click("#btnCreateNewCustomer");
	// TODO: Wie kann ich das öffnen des Kundenmanagements testen?
	// Assert.assertEquals(this.getViewTuple().getViewModel().???,btnCreateNewCustomer.getOnAction());
    }
}
