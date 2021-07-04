package br.com.wishlist.usecase;

import br.com.wishlist.error.exception.ApiException;
import br.com.wishlist.gateway.ItemGateway;
import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindItemsUseCaseTest {

    @InjectMocks
    private FindItemsUseCase findItemsUseCase;

    @Mock
    private ItemGateway itemGateway;

    @Test
    public void given_AClientHasItemsOnWishlist_When_FindAll_Then_ExpectedAListOfItems() {
        var validItem = MockUtil.getValidItem();
        var pageable = MockUtil.getPageable();
        when(itemGateway.findAll(validItem.getClientId(), pageable)).thenReturn(Collections.singletonList(validItem));
        findItemsUseCase.findAll(validItem.getClientId(), pageable);
        verify(itemGateway).findAll(validItem.getClientId(), pageable);
    }

    @Test
    public void given_AClientHasItemsOnWishlist_When_CountAll_Then_ExpectedItemsQuantity() {
        var validItem = MockUtil.getValidItem();
        when(itemGateway.count(validItem.getClientId())).thenReturn(1L);
        findItemsUseCase.count(validItem.getClientId());
        verify(itemGateway).count(validItem.getClientId());
    }

    @Test
    public void given_ExistentItemIdInClientWishlist_When_FindByItemId_Then_ExpectedItem() {
        var validItem = MockUtil.getValidItem();
        when(itemGateway.find(validItem.getClientId(), validItem.getItemId())).thenReturn(Optional.of(validItem));
        findItemsUseCase.find(validItem.getClientId(), validItem.getItemId());
        verify(itemGateway).find(validItem.getClientId(), validItem.getItemId());
    }

    @Test
    public void given_NonexistentItemtIdInClientWishlist_When_findByItemId_Then_ExpectedException() {
        var validItem = MockUtil.getValidItem();
        when(itemGateway.find(validItem.getClientId(), validItem.getItemId())).thenReturn(Optional.empty());
        assertThrows(ApiException.class,
                () -> findItemsUseCase.find(validItem.getClientId(), validItem.getItemId()));
        verify(itemGateway).find(validItem.getClientId(), validItem.getItemId());
    }

}