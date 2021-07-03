package br.com.wishlist.restapi.error.handler;

import br.com.wishlist.error.exception.BaseException;
import br.com.wishlist.error.exception.NotFoundException;
import br.com.wishlist.error.exception.WishListProductAlreadyAddedException;
import br.com.wishlist.error.exception.WishlistItemExceededException;
import br.com.wishlist.error.exception.WishlistProductNotFoundException;
import br.com.wishlist.restapi.WishlistController;
import br.com.wishlist.restapi.error.response.ErrorDetailResponse;
import br.com.wishlist.restapi.error.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;


@Slf4j
@RestControllerAdvice(assignableTypes = WishlistController.class)
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {WishlistItemExceededException.class,
        WishListProductAlreadyAddedException.class})
    @ResponseBody
    protected ResponseEntity<ErrorResponse> handleBadRequest(final Throwable ex) {

        var error = ErrorDetailResponse.builder()
            .code(BAD_REQUEST.name())
            .title(BAD_REQUEST.name())
            .detail(ex.getMessage())
            .build();

        return status(BAD_REQUEST).body(ErrorResponse.builder().errors(List.of(error)).build());
    }

    @ExceptionHandler(value = WishlistProductNotFoundException.class)
    @ResponseBody
    protected ResponseEntity<ErrorResponse> handleNotFound(final Throwable ex) {

        var error = ErrorDetailResponse.builder()
            .code(NOT_FOUND.name())
            .title(NOT_FOUND.name())
            .detail(ex.getMessage())
            .build();

        return status(NOT_FOUND).body(ErrorResponse.builder().errors(List.of(error)).build());
    }

}
