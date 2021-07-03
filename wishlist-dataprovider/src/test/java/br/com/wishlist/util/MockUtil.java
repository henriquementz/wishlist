package br.com.wishlist.util;

import br.com.wishlist.database.entity.ItemEntity;

import java.time.OffsetDateTime;

public final class MockUtil {

    public static ItemEntity getValidEntity() {
        return ItemEntity.builder()
                .productId(484378432L)
                .clientId(1L)
                .bought(0L)
                .desired(3L)
                .addedAt(OffsetDateTime.now())
                .build();
    }

}
