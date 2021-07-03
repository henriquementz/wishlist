package br.com.wishlist.usecase;

import br.com.wishlist.domain.Item;
import br.com.wishlist.error.exception.WishListProductAlreadyAddedException;
import br.com.wishlist.error.exception.WishlistItemExceededException;
import br.com.wishlist.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddItemUseCase {

    private final ItemGateway itemGateway;

    public Item add(final Item item) {

        log.info("WISHLIST_ITEM_SAVE | Item was saved successfully | Item: {}", item);

        var itemsOnWishlist = itemGateway.countByClientId(item.getClientId());

        log.info("WISHLIST_ITEM_SAVE | Wishlist has {} item(s)", itemsOnWishlist);

        if (itemsOnWishlist >= 20) {

            log.error("ERROR_WISHLIST_ITEM_SAVE | Wishlist is fully.");
            throw new WishlistItemExceededException("A lista de desejos já contem 20 itens.");

        } else if (itemGateway.isProductAlreadyAdded(item.getClientId(), item.getProductId())) {

            log.error("ERROR_WISHLIST_ITEM_SAVE | Product is already saved on wishlist.");
            throw new WishListProductAlreadyAddedException("O produto já foi adicionado na lista de desejos.");

        }

        return itemGateway.save(item);
    }

}
