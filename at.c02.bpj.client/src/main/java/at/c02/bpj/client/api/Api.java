package at.c02.bpj.client.api;

import java.io.IOException;
import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.c02.bpj.client.api.model.ServerError;
import eu.lestard.easydi.EasyDI;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Basisklasse für den Zugriff auf die Server-Apis
 */
public class Api {
	private static final Logger logger = LoggerFactory.getLogger(Api.class);

	private static Retrofit retrofit;

	private static UserCredentialsAuthenticator userCredentialsAuthenticator;

	public static void initialize(EasyDI context) {
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		initializeAuthentication(httpClient);
		addRequestLogging(httpClient);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/api/")
				.addConverterFactory(JacksonConverterFactory.create(objectMapper)).client(httpClient.build()).build();

		// Alle Api-Services müssen hier registriert werden
		createAndRegisterService(ArticleApi.class, context);
		createAndRegisterService(CategoryApi.class, context);
		createAndRegisterService(CustomerApi.class, context);
		createAndRegisterService(EmployeeApi.class, context);
		createAndRegisterService(OfferApi.class, context);
		createAndRegisterService(LoginApi.class, context);

	}

	/**
	 * fügt zum HttpClient Logging der Requests hinzu. Muss letzter
	 * Request-Interceptor sein.
	 * 
	 * @param httpClient
	 */
	private static void addRequestLogging(OkHttpClient.Builder httpClient) {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(Level.BODY);
		httpClient.addNetworkInterceptor(logging);
	}

	/**
	 * Authentifizierung für jede Request
	 */
	private static void initializeAuthentication(Builder httpClient) {
		userCredentialsAuthenticator = new UserCredentialsAuthenticator();
		httpClient.addNetworkInterceptor(userCredentialsAuthenticator);
	}

	public static void setUserCredientials(String username, String password) {
		userCredentialsAuthenticator.setCredentials(username, password);
	}

	/**
	 * Initialisiert einen API-Service und legt ihn in den Context
	 * 
	 * @param serviceClass
	 * @param context
	 */
	private static <SERVICE_CLASS> void createAndRegisterService(Class<SERVICE_CLASS> serviceClass, EasyDI context) {
		SERVICE_CLASS service = retrofit.create(serviceClass);
		context.bindInstance(serviceClass, service);
	}

	/**
	 * holt die Fehlermeldung aus einer Response, die nicht erfolgreich war
	 * 
	 * @param response
	 * @return Server-Error: die Fehlermeldung
	 */
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
