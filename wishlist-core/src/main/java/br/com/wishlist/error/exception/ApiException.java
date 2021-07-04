package br.com.wishlist.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -6349375893215769970L;
    
    private final HttpStatus httpStatus;

    private final LocalDateTime timestamp;

    private final String codigoErro;

    private final Map<String, Serializable> properties = new LinkedHashMap<>();

    private ApiException(HttpStatus httpStatus, ErrorCode codigoErro) {
        super("Codigo de erro " + codigoErro.getNumber());
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.codigoErro = codigoErro.getNumber();
    }

    public ApiException(ErrorCode codigoErro) {
        this(HttpStatus.PRECONDITION_FAILED, codigoErro);
    }

    public ApiException(ErrorCode codigoErro, HttpStatus httpStatus) {
        this(httpStatus, codigoErro);
    }

    public static ApiException erroInternoServidor() {
        return new ApiException(WishListErrorCode.ERRO_INTERNO_SERVIDOR,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Map<String, Serializable> getProperties() {
        return properties;
    }

    public Object[] getPropertyValues() {
        return properties.values().toArray();
    }

    public ApiException set(String name, Serializable value) {
        properties.put(name, value);
        return this;
    }
}
