package br.com.wishlist.gateway;

import br.com.wishlist.domain.Item;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@EntityScan(basePackages = "br.com.wishlist")
public interface ItemGateway {

    Item save(final Item item);

    Optional<Item> find(final Long clientId, final Long productId);

    List<Item> findAll(final Long clientId, Pageable pageable);

    Long countByClientId(final Long clientId);

    Long countAll(final Long clientId);

    boolean isAlreadyAdded(final Long clientId, final Long productId);

    void delete(final Long clientId, final Long productId);

}
