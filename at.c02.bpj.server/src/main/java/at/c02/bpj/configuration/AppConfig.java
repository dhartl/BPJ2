package at.c02.bpj.configuration;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Konfigurations-Klasse für die Applikation
 */
@Configuration
public class AppConfig {

	/**
	 * Filter, der Requests in der mitloggt.
	 */
	// Zusätzliches Bean, das in den Applikations-Kontext gelegt wird
	@Bean
	public Filter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(5120);
		return filter;
	}

}
