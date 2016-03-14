package at.c02.bpj.client.service;

import java.io.IOException;

import eu.lestard.easydi.EasyDI;
import retrofit2.Call;
import retrofit2.Response;

public class Services {

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
				throw new ServiceException("Serviceaufruf " + call + " nicht erfolgreich! ");
			}
		} catch (IOException ex) {
			throw new ServiceException(ex);
		}
		return response.body();
	}
}
