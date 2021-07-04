package br.com.wishlist.error.exception;

import br.com.wishlist.error.exception.ErrorCode;

public enum WishListErrorCode implements ErrorCode {

    NOT_FOUND("0001"),
    ERRO_INTERNO_SERVIDOR("0002"),
    URL_INVALIDA("0003"),
    PARAMETRO_OBRIGATORIO("0004"),
    PARAMETRO_INVALIDO("0005"),
    WISHLIST_LENGTH_ERROR("0006"),
    WISHLIST_ITEM_NOT_FOUND("0007"),
    WISHLIST_ITEM_ALREADY_ADDED("0008");

    private final String number;

    WishListErrorCode(String number) {
        this.number = number;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
