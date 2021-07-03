package br.com.wishlist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@ToString
@Getter
@Builder
public class Item {

    private final String id;
    private final Long productId;
    private final Long clientId;
    private final OffsetDateTime addedAt;
    private final Long desired;
    private final Long bought;
}
