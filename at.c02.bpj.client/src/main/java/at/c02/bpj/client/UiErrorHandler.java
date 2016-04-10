package at.c02.bpj.client;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UiErrorHandler implements UncaughtExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(UiErrorHandler.class);

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		handleError(throwable);
	}

	public static void handleError(Throwable throwable) {
		logger.error("{}", throwable);
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Ein Fehler ist Aufgetreten!\n" + throwable.getMessage());
			alert.setTitle("Fehler!");
			alert.setHeaderText(null);
			alert.showAndWait();
		});
	}

}
