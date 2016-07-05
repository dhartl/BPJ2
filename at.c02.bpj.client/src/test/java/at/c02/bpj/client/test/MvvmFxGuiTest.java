package at.c02.bpj.client.test;

import java.util.Collections;
import java.util.List;

import org.loadui.testfx.GuiTest;

import at.c02.bpj.client.Async;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.Scope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import eu.lestard.easydi.EasyDI;
import javafx.scene.Parent;

public abstract class MvvmFxGuiTest extends GuiTest {

	private ViewTuple<? extends FxmlView<? extends ViewModel>, ? extends ViewModel> viewTuple;

	public ViewTuple<? extends FxmlView<? extends ViewModel>, ? extends ViewModel> getViewTuple() {
		return viewTuple;
	}

	@Override
	protected Parent getRootNode() {
		Async.setFxInitialized(true);
		EasyDI context = new EasyDI();
		setupContext(context);
		MvvmFX.setCustomDependencyInjector(context::getInstance);
		viewTuple = FluentViewLoader.fxmlView(getViewClass()).providedScopes(getViewScopes()).load();
		return viewTuple.getView();
	}

	public abstract Class<? extends FxmlView<? extends ViewModel>> getViewClass();

	public abstract void setupContext(EasyDI context);

	public List<Scope> getViewScopes() {
		return Collections.emptyList();
	}

}
