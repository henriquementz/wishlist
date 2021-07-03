package br.com.wishlist.gateway;

import br.com.wishlist.domain.Item;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@EntityScan(basePackages = "br.com.wishlist")
public interface ItemGateway {

    Item save(final Item item);

    Long countByClientId(final Long clientId);

    List<Item> findAll(final Long clientId, Pageable pageable);

    Optional<Item> findProduct(final Long clientId, final Long productId);

    Long countAll(final Long clientId);

    boolean isProductAlreadyAdded(final Long clientId, final Long productId);

    void deleteProduct(final Long clientId, final Long productId);

}
