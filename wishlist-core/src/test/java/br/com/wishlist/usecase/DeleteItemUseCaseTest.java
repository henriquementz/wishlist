package br.com.wishlist.usecase;

import br.com.wishlist.error.exception.WishlistItemExceededException;
import br.com.wishlist.error.exception.WishlistProductNotFoundException;
import br.com.wishlist.gateway.ItemGateway;
import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteItemUseCaseTest {

    @InjectMocks
    private DeleteItemUseCase deleteItemUseCase;

    @Mock
    private ItemGateway itemGateway;

    @Test
    public void given_ItemSavedOnDatabase_When_DeleteItem_Then_ExpectedItemDeleted() {
        var validItem = MockUtil.getValidItem();
        when(itemGateway.findProduct(anyLong(), anyLong())).thenReturn(Optional.of(validItem));
        deleteItemUseCase.deleteProduct(validItem.getClientId(), validItem.getProductId());
        verify(itemGateway).deleteProduct(validItem.getClientId(), validItem.getProductId());
    }

    @Test
    public void given_ItemNotSavedOnDatabase_When_DeleteItem_Then_ExpectedException() {
        var validItem = MockUtil.getValidItem();
        when(itemGateway.findProduct(validItem.getClientId(), validItem.getProductId())).thenReturn(Optional.empty());

        assertThrows(WishlistProductNotFoundException.class, () -> {
            deleteItemUseCase.deleteProduct(validItem.getClientId(), validItem.getProductId());
        });

        verify(itemGateway, times(0)).deleteProduct(validItem.getClientId(), validItem.getProductId());
    }

}