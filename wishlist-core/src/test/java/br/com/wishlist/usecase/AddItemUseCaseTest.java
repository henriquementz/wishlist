package br.com.wishlist.usecase;

import br.com.wishlist.error.exception.ApiException;
import br.com.wishlist.gateway.ItemGateway;
import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddItemUseCaseTest {

    @InjectMocks
    private AddItemUseCase addItemUseCase;

    @Mock
    private ItemGateway itemGateway;

    @Test
    public void given_WishlistNotFully_When_add_Then_ExpectedItemSaved() {
        when(itemGateway.countByClientId(anyLong())).thenReturn(19L);
        when(itemGateway.isAlreadyAdded(anyLong(), anyLong())).thenReturn(false);

        var validItem = MockUtil.getValidItem();
        addItemUseCase.add(validItem);

        verify(itemGateway).countByClientId(validItem.getClientId());
        verify(itemGateway).isAlreadyAdded(validItem.getClientId(), validItem.getItemId());

    }

    @Test
    public void given_WishlistNotFully_When_AddItemAlreadyAdded_Then_ExpectedException() {
        when(itemGateway.countByClientId(anyLong())).thenReturn(19L);
        when(itemGateway.isAlreadyAdded(anyLong(), anyLong())).thenReturn(true);

        var validItem = MockUtil.getValidItem();

        assertThrows(ApiException.class, () -> addItemUseCase.add(validItem));

        verify(itemGateway).countByClientId(validItem.getClientId());
        verify(itemGateway).isAlreadyAdded(validItem.getClientId(), validItem.getItemId());

    }

    @Test
    public void given_WishlistFully_When_Add_Then_ExpectedException() {
        when(itemGateway.countByClientId(anyLong())).thenReturn(20L);

        var validItem = MockUtil.getValidItem();

        assertThrows(ApiException.class, () -> addItemUseCase.add(validItem));

        verify(itemGateway).countByClientId(validItem.getClientId());
        verify(itemGateway, times(0)).isAlreadyAdded(validItem.getClientId(), validItem.getItemId());

    }



}