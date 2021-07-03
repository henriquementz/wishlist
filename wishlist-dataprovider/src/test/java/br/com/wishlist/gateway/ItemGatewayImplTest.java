package br.com.wishlist.gateway;

import br.com.wishlist.database.repository.ItemRepository;
import br.com.wishlist.domain.Item;
import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemGatewayImplTest {

    @InjectMocks
    private ItemGatewayImpl itemGateway;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void given_ValidItemDomain_When_Save_Then_ExpectedItemSaved() {
        var validItemDomain = MockUtil.getValidItem();
        when(itemRepository.save(any())).thenReturn(MockUtil.getValidItemEntity());
        itemGateway.save(validItemDomain);
        verify(itemRepository).save(any());
    }

    @Test
    public void given_ClientWithItemsInWishlist_When_countByClientId_Then_ExpectedQuantityOfItems() {
        var validItemDomain = MockUtil.getValidItem();
        when(itemRepository.countByClientId(validItemDomain.getClientId())).thenReturn(1L);
        itemGateway.countByClientId(validItemDomain.getClientId());
        verify(itemRepository).countByClientId(validItemDomain.getClientId());
    }

    @Test
    public void given_ItemInClientWishlist_When_isProductAlreadyAdded_Then_ExpectedTrue() {
        var validItemDomain = MockUtil.getValidItem();

        when(itemRepository.countByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId())).thenReturn(1L);

        var isAlreadyAdded = itemGateway.isProductAlreadyAdded(validItemDomain.getClientId(),
                validItemDomain.getProductId());

        assertTrue(isAlreadyAdded);

        verify(itemRepository).countByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId());
    }

    @Test
    public void given_ItemNotInClientWishlist_When_isProductAlreadyAdded_Then_ExpectedFalse() {
        var validItemDomain = MockUtil.getValidItem();

        when(itemRepository.countByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId())).thenReturn(0L);

        var isAlreadyAdded = itemGateway.isProductAlreadyAdded(validItemDomain.getClientId(),
                validItemDomain.getProductId());

        assertFalse(isAlreadyAdded);

        verify(itemRepository).countByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId());
    }

    @Test
    public void given_AValidItemInWishlist_When_deleteProduct_Then_ExpectedDeleteItem() {
        var validItemDomain = MockUtil.getValidItem();
        itemGateway.deleteProduct(validItemDomain.getClientId(), validItemDomain.getProductId());
        verify(itemRepository).deleteByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId());
    }

    @Test
    public void given_ClientWithItemsInWishlist_When_FindAll_Then_ExpectedAListOfItems() {
        var validItemDomain = MockUtil.getValidItem();
        PageRequest pageRequest = MockUtil.getPageRequest();

        when(itemRepository.findByClientId(validItemDomain.getClientId(), pageRequest))
                .thenReturn(Collections.singletonList(MockUtil.getValidItemEntity()));

        var itemList = itemGateway.findAll(validItemDomain.getClientId(), pageRequest);

        assertEquals(1, itemList.size());
        verify(itemRepository).findByClientId(validItemDomain.getClientId(), pageRequest);
    }

    @Test
    public void given_AValidItemInWishlist_When_findProduct_Then_ExpectedItem() {
        var validItemDomain = MockUtil.getValidItem();

        when(itemRepository.findByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId())).thenReturn(Optional.of(MockUtil.getValidItemEntity()));

        var item = itemGateway.findProduct(validItemDomain.getClientId(), validItemDomain.getProductId());

        assertTrue(item.isPresent());

        verify(itemRepository).findByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId());
    }

    @Test
    public void given_AnItemNotInWishlist_When_findProduct_Then_ExpectedEmpty() {
        var validItemDomain = MockUtil.getValidItem();

        when(itemRepository.findByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId())).thenReturn(Optional.empty());

        var item = itemGateway.findProduct(validItemDomain.getClientId(), validItemDomain.getProductId());

        assertTrue(item.isEmpty());

        verify(itemRepository).findByClientIdAndProductId(validItemDomain.getClientId(),
                validItemDomain.getProductId());
    }

    @Test
    public void given_AValidClient_When_countAll_Then_ExpectedQuantityOfItems() {
        var validItemDomain = MockUtil.getValidItem();
        when(itemRepository.countByClientId(validItemDomain.getClientId())).thenReturn(1L);
        var quantity = itemGateway.countByClientId(validItemDomain.getClientId());
        assertEquals(1L, quantity);
        verify(itemRepository).countByClientId(validItemDomain.getClientId());
    }

}