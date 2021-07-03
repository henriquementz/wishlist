package br.com.wishlist.usecase;

import br.com.wishlist.error.exception.WishlistProductNotFoundException;
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
        when(itemGateway.countAll(validItem.getClientId())).thenReturn(1L);
        findItemsUseCase.countAll(validItem.getClientId());
        verify(itemGateway).countAll(validItem.getClientId());
    }

    @Test
    public void given_ExistentProductIdInClientWishlist_When_FindByProductId_Then_ExpectedItem() {
        var validItem = MockUtil.getValidItem();
        when(itemGateway.findProduct(validItem.getClientId(), validItem.getProductId())).thenReturn(Optional.of(validItem));
        findItemsUseCase.findByProductId(validItem.getClientId(), validItem.getProductId());
        verify(itemGateway).findProduct(validItem.getClientId(), validItem.getProductId());
    }

    @Test
    public void given_NonexistentProductIdInClientWishlist_When_FindByProductId_Then_ExpectedException() {
        var validItem = MockUtil.getValidItem();
        when(itemGateway.findProduct(validItem.getClientId(), validItem.getProductId())).thenReturn(Optional.empty());
        assertThrows(WishlistProductNotFoundException.class,
                () -> findItemsUseCase.findByProductId(validItem.getClientId(), validItem.getProductId()));
        verify(itemGateway).findProduct(validItem.getClientId(), validItem.getProductId());
    }

}