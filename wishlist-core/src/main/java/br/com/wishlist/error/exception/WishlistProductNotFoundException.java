package br.com.wishlist.error.exception;

public class WishlistProductNotFoundException extends RuntimeException {

    public WishlistProductNotFoundException(final String mensagem) {
        super(mensagem);
    }

}
