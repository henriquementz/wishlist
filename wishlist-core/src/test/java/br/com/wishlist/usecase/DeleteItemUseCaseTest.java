package br.com.wishlist.usecase;

import br.com.wishlist.error.exception.ApiException;
import br.com.wishlist.gateway.ItemGateway;
import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        when(itemGateway.find(anyLong(), anyLong())).thenReturn(Optional.of(validItem));
        deleteItemUseCase.delete(validItem.getClientId(), validItem.getItemId());
        verify(itemGateway).delete(validItem.getClientId(), validItem.getItemId());
    }

    @Test
    public void given_ItemNotSavedOnDatabase_When_DeleteItem_Then_ExpectedException() {
        var validItem = MockUtil.getValidItem();
        when(itemGateway.find(validItem.getClientId(), validItem.getItemId())).thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> {
            deleteItemUseCase.delete(validItem.getClientId(), validItem.getItemId());
        });

        verify(itemGateway, times(0)).delete(validItem.getClientId(), validItem.getItemId());
    }

}