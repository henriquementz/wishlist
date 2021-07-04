package br.com.wishlist.error.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiExceptionTest {

    @Test
    public void given_AnExceptionWithProperties_When_getPropertyValues_Then_ExpectedKeepOrder() {
        ApiException apiException = new ApiException(WishListErrorCode.WISHLIST_ITEM_NOT_FOUND);

        apiException.set("itemId", 1);
        apiException.set("clientId", 4);

        Object[] propertyValues = apiException.getPropertyValues();

        assertEquals(propertyValues[0], 1);
        assertEquals(propertyValues[1], 4);

    }


    @Test
    public void given_AnExceptionWithPropertiesWithNumber_When_getPropertyValues_Then_ExpectedKeepOrder() {
        ApiException apiException = new ApiException(WishListErrorCode.WISHLIST_ITEM_NOT_FOUND);

        apiException.set("4", "itemId");
        apiException.set("1", "clientId");

        Object[] propertyValues = apiException.getPropertyValues();

        assertEquals(propertyValues[0], "itemId");
        assertEquals(propertyValues[1], "clientId");

    }

}