package br.com.wishlist.restapi.mapper;

import br.com.wishlist.domain.Item;
import br.com.wishlist.restapi.model.request.ItemRequest;
import br.com.wishlist.restapi.model.response.ItemResponse;
import br.com.wishlist.restapi.model.response.PageItemResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GroupMapper {

    public static Item mapFromRequest(final ItemRequest itemRequest, final Long clientId) {
        return Item.builder()
            .clientId(clientId)
            .desired(itemRequest.getDesired())
            .itemId(itemRequest.getItemId())
            .build();
    }

    public static ItemResponse mapFromDomain(final Item item) {
        return ItemResponse.builder()
            .clientId(item.getClientId())
            .desired(item.getDesired())
            .bought(item.getBought())
            .itemId(item.getItemId())
            .id(item.getId())
            .addedAt(item.getAddedAt())
            .build();
    }

    public static PageItemResponse mapFromDomain(final List<Item> items, Long quantity) {
        return new PageItemResponse(items.stream().map(GroupMapper::mapFromDomain).collect(Collectors.toList()),
            quantity);
    }

}
