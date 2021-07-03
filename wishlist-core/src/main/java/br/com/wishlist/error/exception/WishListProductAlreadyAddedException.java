package br.com.wishlist.error.exception;

public class WishListProductAlreadyAddedException extends RuntimeException {

    public WishListProductAlreadyAddedException(final String mensagem) {
        super(mensagem);
    }

}
