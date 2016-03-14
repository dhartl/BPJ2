package at.c02.bpj.client.api;

import eu.lestard.easydi.EasyDI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

	public static void initialize(EasyDI context) {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		// set your desired log level
		logging.setLevel(Level.BODY);

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		// add your other interceptors …

		// add logging as last interceptor
		httpClient.addInterceptor(logging); // <-- this is the important line!

		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/api/")
				.addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
		registerService(ArticleApi.class, context, retrofit);
	}

	private static <T> void registerService(Class<T> serviceClass, EasyDI context, Retrofit retrofit) {
		T service = retrofit.create(serviceClass);
		context.bindInstance(serviceClass, service);
	}
}
