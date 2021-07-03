package br.com.wishlist.restapi.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Builder
@Getter
@ToString
public class ItemResponse {

    private final String id;
    private final Long productId;
    private final Long clientId;
    private final OffsetDateTime addedAt;
    private final Long desired;
    private final Long bought;


}
