package br.com.wishlist.restapi.error.handler;

import br.com.wishlist.error.exception.WishListErrorCode;
import br.com.wishlist.error.exception.ApiException;
import br.com.wishlist.restapi.error.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerController {

	private static final String PARAMETRO_INVALIDO = "Parâmetro Inválido";

	private MessageSource messageSource;

    @Autowired
    public ErrorHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex, Locale locale) {
    	String mensagem = ApiErrorResponseUtil.getMessage(ex, messageSource, locale);
		log.error("Erro tratado na API com codigo {}. {}", ex.getCodigoErro(), mensagem, ex);
		ApiErrorResponse error = new ApiErrorResponse(ex, mensagem, locale);
		return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorResponse> handleThrowable(Throwable ex, Locale locale) {
		log.error("Erro inesperado na API", ex);
		ApiException e = new ApiException(WishListErrorCode.ERRO_INTERNO_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		String mensagem = ApiErrorResponseUtil.getMessage(e, messageSource, locale);
		ApiErrorResponse error = new ApiErrorResponse(e, mensagem , locale);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidParams(MethodArgumentTypeMismatchException ex, Locale locale) {
		log.error(PARAMETRO_INVALIDO, ex);
		ApiException e = new ApiException(WishListErrorCode.URL_INVALIDA, HttpStatus.BAD_REQUEST);
		String mensagem = ApiErrorResponseUtil.getMessage(e, messageSource, locale);
		ApiErrorResponse error = new ApiErrorResponse(e, mensagem, locale);
        return new ResponseEntity<>(error, e.getHttpStatus());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidParams(MethodArgumentNotValidException ex, Locale locale) {
		log.error(PARAMETRO_INVALIDO, ex);
		ApiException e = new ApiException(WishListErrorCode.PARAMETRO_OBRIGATORIO, HttpStatus.BAD_REQUEST);
		String mensagem = ApiErrorResponseUtil.getMessage(e, messageSource, locale);
		ApiErrorResponse error = new ApiErrorResponse(e, mensagem, locale);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidParams(HttpMessageNotReadableException ex, Locale locale) {
		log.error(PARAMETRO_INVALIDO, ex);
		ApiException e = new ApiException(WishListErrorCode.PARAMETRO_INVALIDO, HttpStatus.BAD_REQUEST);
		String mensagem = ApiErrorResponseUtil.getMessage(e, messageSource, locale);
		ApiErrorResponse error = new ApiErrorResponse(e, mensagem, locale);
    	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidParams(MissingServletRequestParameterException ex,
			Locale locale) {
		log.error("Parâmetro obrigatório não informado", ex);
		ApiException e = new ApiException(WishListErrorCode.PARAMETRO_OBRIGATORIO, HttpStatus.BAD_REQUEST);
		String mensagem = ApiErrorResponseUtil.getMessage(e, messageSource, locale);
		ApiErrorResponse error = new ApiErrorResponse(e, mensagem, locale);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}