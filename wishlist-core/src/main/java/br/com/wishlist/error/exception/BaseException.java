package br.com.wishlist.error.exception;

import br.com.wishlist.error.enumerator.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

public abstract class BaseException extends RuntimeException {

    protected final ErrorCode code;

    protected final List<ErrorDetail> errors;

    protected BaseException(final ErrorCode code, final ErrorDetail error) {
        this(code, Collections.singletonList(error));
    }

    protected BaseException(final ErrorCode code, final List<ErrorDetail> errors) {
        this(null, code, errors);
    }

    protected BaseException(final String message, final ErrorCode code, final ErrorDetail error) {
        this(message, code, Collections.singletonList(error));
    }

    protected BaseException(final String message, final ErrorCode code, final List<ErrorDetail> errors) {
        super(message);
        this.code = code;
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "BaseException{" +
            "code=" + code +
            ", message=" + super.getMessage() +
            ", errors=" + errors +
            '}';
    }

    @ToString
    @Getter
    @RequiredArgsConstructor
    public static class ErrorDetail {

        private final String field;
        private final String message;

        public ErrorDetail(final String message) {
            this(null, message);
        }

    }

}
