package br.com.wishlist.error.exception;

public class WishlistItemExceededException extends RuntimeException {

    public WishlistItemExceededException(final String mensagem) {
        super(mensagem);
    }

}
