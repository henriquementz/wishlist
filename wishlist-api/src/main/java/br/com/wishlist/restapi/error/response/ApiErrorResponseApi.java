package br.com.wishlist.restapi.error.response;

import java.time.LocalDateTime;

public interface ApiErrorResponseApi {

    LocalDateTime getTimestamp();

    int getHttpStatus();

    String getErrorCode();

    String getMessage();

}
