package br.com.wishlist.restapi.error.response;

import br.com.wishlist.error.exception.ApiException;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;

@Getter
public class ApiErrorResponse implements Serializable, ApiErrorResponseApi {

	private final LocalDateTime timestamp;
	private final int httpStatus;
	private final String codigoErro;
	private final String mensagem;

	public ApiErrorResponse(ApiException ex, String mensagem, Locale locale) {
		this.timestamp = ex.getTimestamp();
		this.httpStatus = ex.getHttpStatus().value();
		this.mensagem = mensagem;
		this.codigoErro = ex.getCodigoErro();
	}
}
