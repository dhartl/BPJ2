package at.c02.bpj.client;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UiErrorHandler implements UncaughtExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(UiErrorHandler.class);

	private static UiErrorHandler INSTANCE;

	private AtomicBoolean dialogShowing = new AtomicBoolean(false);

	public static synchronized UiErrorHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UiErrorHandler();
		}
		return INSTANCE;
	}

	private UiErrorHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		handleError(throwable);
	}

	public void handleError(Throwable throwable) {
		logger.error("{}", throwable);
		Platform.runLater(() -> {
			showAlertDialog(throwable);
		});
	}

	private synchronized void showAlertDialog(Throwable throwable) {
		if (!dialogShowing.get()) {
			dialogShowing.set(true);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Ein Fehler ist Aufgetreten!\n" + throwable.getMessage());
			alert.setTitle("Fehler!");
			alert.setHeaderText(null);
			alert.showAndWait();
			dialogShowing.set(false);
		}
	}

}
