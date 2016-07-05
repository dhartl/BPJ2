package at.c02.bpj.client.ui;

import java.util.ArrayList;
import java.util.List;

import de.saxsys.mvvmfx.FxmlView;

public class Step {
	private String id;
	private String breadcrumbs;
	private Class<? extends FxmlView<?>> viewClass;
	private List<StepAction> stepActions = new ArrayList<>();

	public Step(String id, Class<? extends FxmlView<?>> viewClass) {
		this.id = id;
		this.viewClass = viewClass;
	}

	public String getId() {
		return id;
	}

	public String getBreadcrumbs() {
		return breadcrumbs;
	}

	public void setBreadcrumbs(String breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}

	public Class<? extends FxmlView<?>> getViewClass() {
		return viewClass;
	}

	public List<StepAction> getStepActions() {
		return stepActions;
	}

	public void setStepActions(List<StepAction> stepActions) {
		this.stepActions = stepActions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Step other = (Step) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
