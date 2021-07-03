package br.com.wishlist.restapi.error.response;

import br.com.wishlist.restapi.model.response.MetaResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @NotNull
    private final List<ErrorDetailResponse> errors;

    private final MetaResponse meta;

}
