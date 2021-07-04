package br.com.wishlist.util;

import br.com.wishlist.database.entity.ItemEntity;
import br.com.wishlist.domain.Item;
import org.springframework.data.domain.PageRequest;

import java.time.OffsetDateTime;

public final class MockUtil {

    public static ItemEntity getValidItemEntity() {
        return ItemEntity.builder()
                .id("54326")
                .itemId(484378432L)
                .clientId(1L)
                .bought(0L)
                .desired(3L)
                .addedAt(OffsetDateTime.now())
                .build();
    }

    public static Item getValidItem() {
        return Item.builder()
                .itemId(484378432L)
                .clientId(1L)
                .desired(1L)
                .build();
    }

    public static PageRequest getPageRequest() {
        return PageRequest.of(0, 10);
    }

}
