package br.com.wishlist.gateway;

import br.com.wishlist.database.repository.ItemRepository;
import br.com.wishlist.domain.Item;
import br.com.wishlist.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemGatewayImpl implements ItemGateway {

    private final ItemRepository itemRepository;

    @Override
    public Item save(final Item item) {
        var itemEntity = ItemMapper.mapFromDomain(item);
        var itemEntitySaved = itemRepository.save(itemEntity);
        log.info("WISHLIST_ITEM_SAVED | Item was saved successfully | Item: {}", itemEntitySaved);
        return ItemMapper.mapFromEntity(itemEntitySaved);
    }

    @Override
    public Long countByClientId(final Long clientId) {
        return itemRepository.countByClientId(clientId);
    }

    @Override
    public boolean isAlreadyAdded(final Long clientId, final Long productId) {
        return itemRepository.countByClientIdAndItemId(clientId, productId) > 0;
    }

    @Override
    public void delete(final Long clientId, final Long productId) {
        itemRepository.deleteByClientIdAndItemId(clientId, productId);
        log.info("WISHLIST_ITEM_DELETED | Product was successfully deleted.");
    }

    @Override
    public List<Item> findAll(final Long clientId, final Pageable pageable) {
        return itemRepository.findByClientId(clientId, pageable)
            .stream()
            .map(ItemMapper::mapFromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> find(final Long clientId, final Long productId) {
        var productOptional = itemRepository.findByClientIdAndItemId(clientId, productId);

         if (productOptional.isEmpty()) {
             return Optional.empty();
         } else {
             log.info("WISHLIST_ITEM_FOUND | Product was found: {}.", productOptional.get());
             return Optional.of(ItemMapper.mapFromEntity(productOptional.get()));
         }
    }

    @Override
    public Long countAll(final Long clientId) {
        return itemRepository.countByClientId(clientId);
    }

}
