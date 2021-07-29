package br.com.zupacademy.proposta.requisicoes.cartoes;

import br.com.zupacademy.proposta.requisicoes.cartoes.dto.ConsultaSeCartaoFoiCriadoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartao", url = "${api-cartoes.host}")
public interface ConsultaSeCartaoFoiCriado {
    /*Corrigindo Task: Associar o cartão criado com a proposta, pois para consulta é usado o GET e não POST*/
    @RequestMapping(method = RequestMethod.GET, value = "/cartoes")
    ResponseEntity<ConsultaSeCartaoFoiCriadoResponse> checaSeCartaoFoiCriado(@RequestParam(value = "idProposta") Long id);

}
