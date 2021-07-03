package br.com.wishlist.database.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;

@Getter
@Builder
@ToString
@Document(collection = "items")
public class ItemEntity {

    @Id
    private final String id;

    @Field("product_id")
    private final Long productId;

    @Field("client_id")
    private final Long clientId;

    @Field("added_at")
    private final OffsetDateTime addedAt;

    private final Long desired;

    private final Long bought;

}
