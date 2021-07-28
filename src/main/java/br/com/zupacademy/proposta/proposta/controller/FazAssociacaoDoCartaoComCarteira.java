package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.dto.AssociaCartaoAUmaCarteiraRequest;
import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.proposta.modelo.Carteira;
import br.com.zupacademy.proposta.requisicoes.cartoes.AssociaCartaoAUmaCarteira;
import br.com.zupacademy.proposta.requisicoes.cartoes.dto.AssociaCarteiraRequest;
import br.com.zupacademy.proposta.requisicoes.cartoes.dto.AssociaCarteiraResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FazAssociacaoDoCartaoComCarteira {

    @Autowired
    private AssociaCartaoAUmaCarteira associaCartaoAUmaCarteira;

    public Carteira associa(Cartao cartao, AssociaCartaoAUmaCarteiraRequest associaCartaoAUmaCarteiraRequest, String nomeDaCarteira) {
        String codigoDaCarteira = null;
        try {
            AssociaCarteiraResponse associa = associaCartaoAUmaCarteira
                    .associa(cartao.getNumero(), new AssociaCarteiraRequest(associaCartaoAUmaCarteiraRequest.getEmail(), nomeDaCarteira));
            codigoDaCarteira = associa.getId();

        }catch (FeignException exception){
            if(exception.status() == 422)
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está associado a esta Carteira.");
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Falha ao associar Cartão a Carteira.");
        }

        Assert.hasText(codigoDaCarteira, "O código da Carteira não pode ser nulo.");
        return new Carteira(codigoDaCarteira, cartao);
    }

}
