package br.com.wishlist.restapi.error.response;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class ErrorDetailResponse {

    public final String code;

    public final String title;

    public final String detail;

}
