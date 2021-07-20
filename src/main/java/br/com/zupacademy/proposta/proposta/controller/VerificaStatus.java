package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import br.com.zupacademy.proposta.requisicoes.solicitacao.ConsultaDadosDoSolicitante;
import br.com.zupacademy.proposta.requisicoes.solicitacao.dto.ConsultaDadosRequest;
import br.com.zupacademy.proposta.requisicoes.solicitacao.dto.ConsultaDadosResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificaStatus {

    @Autowired
    private ConsultaDadosDoSolicitante consultaDadosDoSolicitante;

    public String verifica(Proposta proposta){
        try {
            ConsultaDadosResponse solicitacao = consultaDadosDoSolicitante.consulta(new ConsultaDadosRequest(proposta));
            return solicitacao.getResultadoSolicitacao(); /*SEM_RESTRICAO*/
        }catch (FeignException.UnprocessableEntity e){
            return "COM_RESTRICAO";
        }
    }

}
