package br.com.zupacademy.proposta.requisicoes.cartoes;

import br.com.zupacademy.proposta.requisicoes.cartoes.dto.NotificaAvisoDeViagemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cartao-notifica-aviso-de-viagem", url = "${api-cartoes.host}")
public interface NotificaAvisoDeViagem {

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{idCartao}/avisos")
    void notifica(@PathVariable("idCartao") String numeroCartao,
                  @RequestBody NotificaAvisoDeViagemRequest notificaAvisoDeViagemRequest);
}
