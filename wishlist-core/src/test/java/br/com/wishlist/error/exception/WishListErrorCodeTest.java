package br.com.wishlist.error.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;


class WishListErrorCodeTest {

    @Test
    public void given_ApplicationError_When_listAllOfThem_Then_ExpectedThereIsNoRepeatedCode() {

        Set<String> errorCodeList = new HashSet<>();

        for (WishListErrorCode wishListErrorCode : WishListErrorCode.values()) {
            if (!errorCodeList.add(wishListErrorCode.getNumber())) {
                Assertions.fail();
            }
        }
    }

    @Test
    public void given_AListOfError_When_checkIfAllErrorMessagesInPortugueseExists_Then_ExpectedTrue() throws IOException {
        checkIfAllMessagesExists("src/main/resources/messages_pt_BR.properties");
    }

    @Test
    public void given_AListOfError_When_checkIfAllErrorMessagesInEnglishExists_Then_ExpectedTrue() throws IOException {
        checkIfAllMessagesExists("src/main/resources/messages.properties");
    }

    private void checkIfAllMessagesExists(String file) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        for (ErrorCode errorCode : WishListErrorCode.values()) {
            assertTrue(properties.containsKey(errorCode.getNumber()));
        }
    }

}