package br.com.wishlist.usecase;

import br.com.wishlist.domain.Item;
import br.com.wishlist.error.exception.WishlistProductNotFoundException;
import br.com.wishlist.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteItemUseCase {

    private final ItemGateway itemGateway;

    public void deleteProduct(final Long clientId, final Long productId) {

        Optional<Item> itemOptional = itemGateway.findProduct(clientId, productId);

        if (itemOptional.isEmpty()) {
            log.error("ERROR_WISHLIST_ITEM_DELETE | Product was not found on wishlist: {}.", productId);
            throw new WishlistProductNotFoundException("Produto não encontrado na Wishlist");
        }

        itemGateway.deleteProduct(clientId, productId);
    }

}
