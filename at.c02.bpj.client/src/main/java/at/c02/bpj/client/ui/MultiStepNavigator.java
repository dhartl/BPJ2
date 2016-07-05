package at.c02.bpj.client.ui;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.Scope;
import de.saxsys.mvvmfx.ViewTuple;

public class MultiStepNavigator {
	private MultiStepViewModel model;
	private Map<String, Step> steps = new HashMap<>();
	private ViewTuple<? extends FxmlView<?>, ?> viewTuple;

	public Collection<Scope> scopes = Collections.emptyList();

	public MultiStepNavigator() {

	}

	public ViewTuple<? extends FxmlView<?>, ?> getViewTuple() {
		return viewTuple;
	}

	public void setModel(MultiStepViewModel model) {
		this.model = model;
	}

	public void setScopes(Collection<Scope> scopes) {
		this.scopes = scopes;
	}

	public void addStep(Step step) {
		steps.put(step.getId(), step);
	}

	public void navigateTo(String stepId) {
		setStepToModel(steps.get(stepId));
	}

	private void setStepToModel(Step step) {
		model.setBreadcrumbs(step.getBreadcrumbs());
		model.getStepActions().setAll(step.getStepActions());
		viewTuple = FluentViewLoader.fxmlView(step.getViewClass()).providedScopes(scopes).load();
		model.setViewProperty(viewTuple.getView());
	}
}
