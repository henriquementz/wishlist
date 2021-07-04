package br.com.wishlist.restapi.error.response;

import java.time.LocalDateTime;

//@ApiModel(value = "Erro")
public interface ApiErrorResponseApi {

    //@ApiModelProperty(value = "Data no qual ocorreu o erro. O formato da data obedece o padrão ISO 8601", required = true)
    LocalDateTime getTimestamp();

    //@ApiModelProperty(value = "Código do status http do erro", required = true)
    int getHttpStatus();

    //@ApiModelProperty(value = "Código do erro", required = true)
    String getCodigoErro();

    //@ApiModelProperty(value = "Mensagem de erro", required = true)
    String getMensagem();

}
