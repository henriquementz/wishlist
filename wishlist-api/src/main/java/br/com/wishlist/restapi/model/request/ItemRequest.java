package br.com.wishlist.restapi.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ItemRequest {

    @NotEmpty
    private final Long productId;

    @NotEmpty
    private final Long desired;

}
