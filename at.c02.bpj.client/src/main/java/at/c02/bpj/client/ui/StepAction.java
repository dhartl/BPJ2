package at.c02.bpj.client.ui;

public class StepAction {
	private String title;
	private Runnable action;

	public StepAction(String title, Runnable action) {
		super();
		this.title = title;
		this.action = action;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Runnable getAction() {
		return action;
	}

	public void setAction(Runnable action) {
		this.action = action;
	}

}
