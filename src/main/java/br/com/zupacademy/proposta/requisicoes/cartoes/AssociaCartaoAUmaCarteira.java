package br.com.zupacademy.proposta.requisicoes.cartoes;

import br.com.zupacademy.proposta.requisicoes.cartoes.dto.AssociaCarteiraRequest;
import br.com.zupacademy.proposta.requisicoes.cartoes.dto.AssociaCarteiraResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cartao-associa-a-carteira", url = "${api-cartoes.host}")
public interface AssociaCartaoAUmaCarteira {

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{idCartao}/carteiras")
    AssociaCarteiraResponse associa(@PathVariable("idCartao") String numeroCartao,
                                    @RequestBody AssociaCarteiraRequest request);


}
