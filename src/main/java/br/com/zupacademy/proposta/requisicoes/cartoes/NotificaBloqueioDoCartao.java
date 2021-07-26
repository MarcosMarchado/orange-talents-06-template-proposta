package br.com.zupacademy.proposta.requisicoes.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "cartao-notifica-bloqueio", url = "${api-cartoes.host}")
public interface NotificaBloqueioDoCartao {

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{idCartao}/bloqueios")
    void notifica(@PathVariable("idCartao") String numeroCartao, @RequestBody Map<String, String> sistemaResponsavel);

}
