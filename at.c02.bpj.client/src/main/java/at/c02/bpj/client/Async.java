package at.c02.bpj.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.application.Platform;

public class Async {
	private static boolean fxInitialized;
	private static AsyncExecutor executor;

	public static boolean isFxInitialized() {
		return fxInitialized;
	}

	public static void setFxInitialized(boolean fxInitialized) {
		Async.fxInitialized = fxInitialized;
	}

	public static void execute(Runnable runnable) {
		if (executor == null) {
			createExecutor();
		}
		executor.execute(runnable);
	}

	private static void createExecutor() {
		executor = new AsyncExecutor();
	}

	public static <T> void execute(Supplier<T> supplier, Consumer<T> callback) {
		if (executor == null) {
			createExecutor();
		}
		executor.execute(supplier, callback);
	}

	public static <T> void executeUILoad(Supplier<T> supplier, Consumer<T> callback) {
		if (executor == null) {
			createExecutor();
		}
		executor.execute(supplier, (returnValue) -> {
			if (isFxInitialized()) {
				Platform.runLater(() -> callback.accept(returnValue));
			} else {
				callback.accept(returnValue);
			}
		});
	}

	private static class AsyncExecutor {

		private ExecutorService executorService;

		private AsyncExecutor() {
			executorService = new ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
					new AsyncThreadFactory());
		}

		public <T> void execute(Supplier<T> supplier, Consumer<T> callback) {
			executorService.execute(() -> callback.accept(supplier.get()));
		}

		public void execute(Runnable runnable) {
			executorService.execute(runnable);
		}

	}

	
	private static class AsyncThreadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = Executors.defaultThreadFactory().newThread(r);
			thread.setUncaughtExceptionHandler(UiErrorHandler.getInstance());
			return thread;
		}

	}
}
