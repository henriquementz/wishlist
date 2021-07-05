package br.com.wishlist.usecase;

import br.com.wishlist.domain.Item;
import br.com.wishlist.error.exception.ApiException;
import br.com.wishlist.error.exception.WishListErrorCode;
import br.com.wishlist.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddItemUseCase {

    public static final int MAXIMUM_WISHLIST_LENGTH = 20;

    private final ItemGateway itemGateway;

    public Item add(final Item item) {

        var itemsOnWishlist = itemGateway.countByClientId(item.getClientId());

        if (itemsOnWishlist >= MAXIMUM_WISHLIST_LENGTH) {
            log.error("ERROR_WISHLIST_ITEM_SAVE | Wishlist is fully |  clientId: {}, itemId: {}",
                    item.getClientId(), item.getItemId());
            throw new ApiException(WishListErrorCode.WISHLIST_LENGTH_ERROR);
        } else if (itemGateway.isAlreadyAdded(item.getClientId(), item.getItemId())) {
            log.error("ERROR_WISHLIST_ITEM_SAVE | Item is already saved on wishlist | clientId: {}, itemId: {}",
                    item.getClientId(), item.getItemId());
            throw new ApiException(WishListErrorCode.WISHLIST_ITEM_ALREADY_ADDED);
        }

        var itemSaved = itemGateway.save(item);
        log.error("WISHLIST_ITEM_SAVED | Item was saved successfully on wishlist | response: {}.", itemSaved);

        return itemSaved;
    }

}
