package br.com.wishlist.util;

import br.com.wishlist.domain.Item;

public final class MockUtil {
    public static Item getValidItem() {
        return Item.builder()
                .productId(342324L)
                .clientId(1L)
                .build();
    }
}
