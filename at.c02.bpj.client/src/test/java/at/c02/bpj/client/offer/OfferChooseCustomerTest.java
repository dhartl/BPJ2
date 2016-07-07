package at.c02.bpj.client.offer;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.test.MvvmFxGuiTest;
import at.c02.bpj.client.test.TestData;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.Scope;
import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.easydi.EasyDI;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

@RunWith(MockitoJUnitRunner.class)
public class OfferChooseCustomerTest extends MvvmFxGuiTest {

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

    @Override
    public List<Scope> getViewScopes() {
	return Lists.newArrayList(new OfferScope());
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
	click(cbxCustomer).press(KeyCode.DOWN).press(KeyCode.ENTER);

	Label lblCustomerCity = find("#lblCustomerCity");
	Label lblCustomerStreet = find("#lblCustomerStreet");
	Label lblCustomerHouseNr = find("#lblCustomerHouseNr");
	Label lblCustomerPostCode = find("#lblCustomerPostCode");
	Label lblCustomerContactPartnerFN = find("#lblCustomerContactPartnerFN");
	Label lblCustomerContactPartnerLN = find("#lblCustomerContactPartnerLN");
	//

	Assert.assertEquals("Graz", lblCustomerCity.getText());
	Assert.assertEquals("A-Straße", lblCustomerStreet.getText());
	Assert.assertEquals("10", lblCustomerHouseNr.getText());
	Assert.assertEquals("8010", lblCustomerPostCode.getText());
	Assert.assertEquals("Max", lblCustomerContactPartnerFN.getText());
	Assert.assertEquals("Mustermann", lblCustomerContactPartnerLN.getText());

    }
}