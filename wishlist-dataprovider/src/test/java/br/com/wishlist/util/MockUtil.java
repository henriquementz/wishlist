package br.com.wishlist.util;

import br.com.wishlist.database.entity.ItemEntity;
import br.com.wishlist.domain.Item;

import java.time.OffsetDateTime;

public final class MockUtil {

    public static ItemEntity getValidItemEntity() {
        return ItemEntity.builder()
                .id("54326")
                .productId(484378432L)
                .clientId(1L)
                .bought(0L)
                .desired(3L)
                .addedAt(OffsetDateTime.now())
                .build();
    }

    public static Item getValidItem() {
        return Item.builder()
                .productId(484378432L)
                .clientId(1L)
                .desired(1L)
                .build();
    }

}
