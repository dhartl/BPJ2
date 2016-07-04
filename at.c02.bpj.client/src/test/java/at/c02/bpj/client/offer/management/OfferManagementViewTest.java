package at.c02.bpj.client.offer.management;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.article.ArticleView;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.CategoryService;
import at.c02.bpj.client.service.OfferService;
import at.c02.bpj.client.test.MvvmFxGuiTest;
import at.c02.bpj.client.test.TestData;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.easydi.EasyDI;

@RunWith(MockitoJUnitRunner.class)
public class OfferManagementViewTest extends MvvmFxGuiTest {

		@Mock
		private OfferService offerService;
		
		private Offer offer1;
		private Offer offer2;
		private Offer offer3;

		@Override
		public Class<? extends FxmlView<? extends ViewModel>> getViewClass() {
			return OfferManagementView.class;
		}

		@Override
		public void setupContext(EasyDI context) {
			
		}
}
