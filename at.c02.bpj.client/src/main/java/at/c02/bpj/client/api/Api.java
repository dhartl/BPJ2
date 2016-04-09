package at.c02.bpj.client.api;

import java.io.IOException;
import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.c02.bpj.client.api.model.ServerError;
import eu.lestard.easydi.EasyDI;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Api {
	private static final Logger logger = LoggerFactory.getLogger(Api.class);

	private static Retrofit retrofit;

	public static void initialize(EasyDI context) {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		// set your desired log level
		logging.setLevel(Level.BODY);

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		// add your other interceptors …

		// add logging as last interceptor
		httpClient.addInterceptor(logging); // <-- this is the important line!
		retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/api/")
				.addConverterFactory(JacksonConverterFactory.create()).client(httpClient.build()).build();
		registerService(ArticleApi.class, context, retrofit);
	}

	private static <T> void registerService(Class<T> serviceClass, EasyDI context, Retrofit retrofit) {
		T service = retrofit.create(serviceClass);
		context.bindInstance(serviceClass, service);
	}

	public static ServerError parseError(retrofit2.Response<?> response) {
		if (retrofit == null) {
			return null;
		}
		Converter<ResponseBody, ServerError> converter = retrofit.responseBodyConverter(ServerError.class,
				new Annotation[0]);
		ServerError error = null;
		try {
			error = converter.convert(response.errorBody());
		} catch (IOException e) {
			logger.error("failed to parse serverError", e);
		}
		return error;
	}
}
