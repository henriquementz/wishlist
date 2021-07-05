package br.com.wishlist.usecase;

import br.com.wishlist.domain.Item;
import br.com.wishlist.error.exception.ApiException;
import br.com.wishlist.error.exception.WishListErrorCode;
import br.com.wishlist.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindItemsUseCase {

    private final ItemGateway itemGateway;

    public List<Item> findAll(final Long clientId, final Pageable pageable) {
        return itemGateway.findAll(clientId, pageable);
    }

    public Long count(final Long clientId) {
        return itemGateway.count(clientId);
    }

    public Item find(final Long clientId, final Long itemId) {

        var itemOptional = itemGateway.find(clientId, itemId);

        if (itemOptional.isEmpty()) {
            log.error("WISHLIST_ITEM_FIND | Item was not found on wishlist | clientId: {}, itemId: {}", clientId, itemId);
            throw new ApiException(WishListErrorCode.WISHLIST_ITEM_NOT_FOUND);
        }

        log.info("WISHLIST_ITEM_FOUND | Item was retrieved successfully | response: {}", itemOptional.get());

        return itemOptional.get();
    }

}
