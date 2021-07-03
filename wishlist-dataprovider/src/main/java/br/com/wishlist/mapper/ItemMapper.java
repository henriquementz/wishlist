package br.com.wishlist.mapper;

import br.com.wishlist.database.entity.ItemEntity;
import br.com.wishlist.domain.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemMapper {

    public static ItemEntity mapFromDomain(final Item item) {
        return ItemEntity.builder()
            .addedAt(OffsetDateTime.now())
            .bought(0L)
            .desired(item.getDesired())
            .clientId(item.getClientId())
            .productId(item.getProductId())
            .build();
    }

    public static Item mapFromEntity(final ItemEntity itemEntity) {
        return Item.builder()
            .addedAt(itemEntity.getAddedAt())
            .bought(itemEntity.getBought())
            .desired(itemEntity.getDesired())
            .productId(itemEntity.getProductId())
            .id(itemEntity.getId())
            .clientId(itemEntity.getClientId())
            .build();
    }

}
