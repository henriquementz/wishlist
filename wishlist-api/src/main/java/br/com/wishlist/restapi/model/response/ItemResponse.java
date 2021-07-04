package br.com.wishlist.restapi.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Builder
@Getter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ItemResponse {

    private final String id;
    private final Long itemId;
    private final Long clientId;
    private final OffsetDateTime addedAt;
    private final Long desired;
    private final Long bought;

}
