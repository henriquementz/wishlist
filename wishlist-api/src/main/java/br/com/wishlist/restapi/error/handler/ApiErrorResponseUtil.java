package br.com.wishlist.restapi.error.handler;

import br.com.wishlist.error.exception.ApiException;
import org.springframework.context.MessageSource;

import java.util.Locale;

public final class ApiErrorResponseUtil {

	private ApiErrorResponseUtil() {
		super();
	}
	
	public static String getMessage(ApiException ex, MessageSource messageSource, Locale locale) {
        return messageSource.getMessage(ex.getCodigoErro(), ex.getPropertyValues(), locale);
    }

}
