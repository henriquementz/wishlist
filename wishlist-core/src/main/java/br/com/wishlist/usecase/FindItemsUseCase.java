package br.com.wishlist.usecase;

import br.com.wishlist.domain.Item;
import br.com.wishlist.error.exception.WishlistProductNotFoundException;
import br.com.wishlist.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindItemsUseCase {

    private final ItemGateway itemGateway;

    public List<Item> findAll(final Long clientId, final Pageable pageable) {
        return itemGateway.findAll(clientId, pageable);
    }

    public Long countAll(final Long clientId) {
        return itemGateway.countAll(clientId);
    }

    public Item findByProductId(final Long clientId, final Long productId) {

        var itemOptional = itemGateway.findProduct(clientId, productId);

        if (itemOptional.isEmpty()) {
            log.error("WISHLIST_ITEM_FIND | Product was not found on wishlist.");
            throw new WishlistProductNotFoundException("Produto não encontrado na Wishlist");
        }

        log.info("WISHLIST_ITEM_FOUND | Product was retrieved successfully: {}", itemOptional.get());

        return itemOptional.get();
    }

}
