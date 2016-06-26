package at.c02.bpj.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.application.Platform;

public class Async {
	private static AsyncExecutor executor;

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
			Platform.runLater(() -> callback.accept(returnValue));
		});
	}

	private static class AsyncExecutor {

		private ExecutorService executorService;

		private AsyncExecutor() {
			executorService = new ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
		}

		public <T> void execute(Supplier<T> supplier, Consumer<T> callback) {
			executorService.execute(() -> callback.accept(supplier.get()));
		}

		public void execute(Runnable runnable) {
			executorService.execute(runnable);
		}

	}

}
