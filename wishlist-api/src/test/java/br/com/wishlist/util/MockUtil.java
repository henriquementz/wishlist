package br.com.wishlist.util;

import br.com.wishlist.domain.Item;
import br.com.wishlist.restapi.model.request.ItemRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class MockUtil {

    @Autowired
    private ObjectMapper mapper;

    public Item getValidItem() {
        return Item.builder()
                .productId(342324L)
                .clientId(1L)
                .desired(1L)
                .bought(0L)
                .addedAt(OffsetDateTime.now())
                .id("4543354")
                .build();
    }

    public List<Item> getListOfValidItems() {
        return Collections.singletonList(getValidItem());
    }

    public String getJsonItemRequest() throws JsonProcessingException {
        return toJson(ItemRequest.builder()
                .desired(1L)
                .productId(4343L)
                .build());
    }

    private String toJson(ItemRequest request) throws JsonProcessingException {
        return mapper.writeValueAsString(request);
    }
}
