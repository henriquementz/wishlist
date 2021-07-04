package br.com.wishlist.util;

import br.com.wishlist.domain.Item;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

public final class MockUtil {

    public static Item getValidItem() {
        return Item.builder()
                .itemId(342324L)
                .clientId(1L)
                .build();
    }

    public static List<Item> getValidItemList() {
        return Collections.singletonList(getValidItem());
    }

    public static PageRequest getPageable() {
        return PageRequest.of(0, 1);
    }
}
