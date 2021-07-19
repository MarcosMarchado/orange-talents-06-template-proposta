package br.com.zupacademy.proposta.requisicoes.solicitacao;

import br.com.zupacademy.proposta.requisicoes.solicitacao.dto.ConsultaDadosRequest;
import br.com.zupacademy.proposta.requisicoes.solicitacao.dto.ConsultaDadosResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacao", url = "http://localhost:9999/api")
public interface ConsultaDadosDoSolicitante {

    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao")
    ConsultaDadosResponse consulta(@RequestBody ConsultaDadosRequest consultaDadosRequest);

}
