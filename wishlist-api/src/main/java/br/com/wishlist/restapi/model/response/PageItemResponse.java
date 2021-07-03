package br.com.wishlist.restapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class PageItemResponse {

    private final List<ItemResponse> itens;
    private final Long quantity;

}
