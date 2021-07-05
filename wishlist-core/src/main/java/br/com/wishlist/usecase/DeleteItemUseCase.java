package br.com.wishlist.usecase;

import br.com.wishlist.error.exception.ApiException;
import br.com.wishlist.error.exception.WishListErrorCode;
import br.com.wishlist.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteItemUseCase {

    private final ItemGateway itemGateway;

    public void delete(final Long clientId, final Long itemId) {

        var itemOptional = itemGateway.find(clientId, itemId);

        if (itemOptional.isEmpty()) {
            log.error("ERROR_WISHLIST_ITEM_DELETE | Item was not found on wishlist | clientId: {}, itemId: {}",
                    clientId, itemId);
            throw new ApiException(WishListErrorCode.WISHLIST_ITEM_NOT_FOUND);
        }

        itemGateway.delete(clientId, itemId);
        log.error("WISHLIST_ITEM_DELETED | Item was deleted successfully on wishlist | clientId: {}, itemId: {}.",
                clientId, itemId);
    }

}
