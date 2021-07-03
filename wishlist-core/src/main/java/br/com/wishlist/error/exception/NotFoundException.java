package br.com.wishlist.error.exception;

import br.com.wishlist.error.enumerator.ErrorCode;

public class NotFoundException extends BaseException {

    private static final String DEFAULT_MESSAGE = "Recurso não encontrado";

    public NotFoundException(final String field) {
        super(ErrorCode.INVALID, buildErrorDetailWithField(field));
    }

    private static ErrorDetail buildErrorDetailWithField(final String field) {
        return new ErrorDetail(field, DEFAULT_MESSAGE);
    }

}
