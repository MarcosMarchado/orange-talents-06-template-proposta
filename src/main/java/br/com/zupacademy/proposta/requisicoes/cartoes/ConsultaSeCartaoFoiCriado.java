package br.com.zupacademy.proposta.requisicoes.cartoes;

import br.com.zupacademy.proposta.requisicoes.cartoes.dto.ConsultaSeCartaoFoiCriadoRequest;
import br.com.zupacademy.proposta.requisicoes.cartoes.dto.ConsultaSeCartaoFoiCriadoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cartao", url = "${api-cartoes.host}")
public interface ConsultaSeCartaoFoiCriado {

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes")
    ResponseEntity<ConsultaSeCartaoFoiCriadoResponse> checaSeCartaoFoiCriado(
            @RequestBody ConsultaSeCartaoFoiCriadoRequest seCartaoFoiCriadoRequest);

}
