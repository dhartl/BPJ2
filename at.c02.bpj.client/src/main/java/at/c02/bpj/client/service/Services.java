package at.c02.bpj.client.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.c02.bpj.client.api.Api;
import at.c02.bpj.client.api.model.ServerError;
import eu.lestard.easydi.EasyDI;
import retrofit2.Call;
import retrofit2.Response;

public class Services {
	private static final Logger logger = LoggerFactory.getLogger(Services.class);

	public static void initialize(EasyDI context) {
		registerService(ArticleService.class, context);
	}

	private static <T> void registerService(Class<T> serviceClass, EasyDI context) {
		T service = context.getInstance(serviceClass);
		context.bindInstance(serviceClass, service);
	}

	public static <S> S executeCall(Call<S> call) throws ServiceException {
		Response<S> response;
		try {
			response = call.execute();
			if (!response.isSuccess()) {
				handleUnsuccessfulResponse(response);
			}
		} catch (IOException ex) {
			throw new ServiceException(ex);
		}
		return response.body();
	}

	public static <S> void handleUnsuccessfulResponse(Response<S> response) throws ServiceException {
		String message = null;
		ServerError parseError = Api.parseError(response);
		if (parseError != null) {
			message = parseError.getMessage();
		}
		if (message == null) {
			message = response.code() + " " + response.message();
		}
		logger.error("failed to call Service: {}", message);
		throw new ServiceException(message);
	}
}
