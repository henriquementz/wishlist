package br.com.wishlist.restapi.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Builder
public class MetaResponse {

    @NotNull
    private final Integer totalRecords;

    @NotNull
    private final Integer totalPages;

    @NotBlank
    private final String requestDateTime;

}
