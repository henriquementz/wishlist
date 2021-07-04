package br.com.wishlist.database.repository;

import br.com.wishlist.database.entity.ItemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<ItemEntity, String> {

    Long countByClientId(final Long clientId);

    Long countByClientIdAndItemId(final Long clientId, final Long itemId);

    List<ItemEntity> findByClientId(Long clientId, Pageable pageable);

    Optional<ItemEntity> findByClientIdAndItemId(final Long clientId, final Long itemId);

    void deleteByClientIdAndItemId(final Long clientId, final Long itemId);

}
