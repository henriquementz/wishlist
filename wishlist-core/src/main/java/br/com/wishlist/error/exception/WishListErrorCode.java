package br.com.wishlist.error.exception;

public enum WishListErrorCode implements ErrorCode {

    NOT_FOUND("0001"),
    INTERNAL_SERVER_ERROR("0002"),
    INVALID_URI("0003"),
    PARAMETER_REQUIRED("0004"),
    INVALID_PARAMETER("0005"),
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
