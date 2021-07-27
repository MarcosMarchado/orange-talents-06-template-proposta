package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.dto.NovoAvisoDeViagemRequest;
import br.com.zupacademy.proposta.proposta.modelo.AvisoDeViagem;
import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.proposta.repository.CartaoRepository;
import br.com.zupacademy.proposta.requisicoes.cartoes.NotificaAvisoDeViagem;
import br.com.zupacademy.proposta.requisicoes.cartoes.dto.NotificaAvisoDeViagemRequest;
import br.com.zupacademy.proposta.utilitarios.PegaIPRequisicao;
import br.com.zupacademy.proposta.validacao.exception.ObjectNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
public class AvisoDeViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NotificaAvisoDeViagem notificaAvisoDeViagem;

    @Transactional
    @PostMapping("/{id}/aviso-viagem")
    public ResponseEntity<?> registraAvisoDeViagem(@PathVariable Long id,
                                                   HttpServletRequest request,
                                                   @Valid @RequestBody NovoAvisoDeViagemRequest novoAvisoDeViagemRequest){

        String userAgent = request.getHeader("User-Agent");
        String ipRequest = PegaIPRequisicao.getIpRequest(request);

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado"));

        AvisoDeViagem avisoDeViagem = novoAvisoDeViagemRequest.converter(userAgent, ipRequest, cartao);
        notificaAvisoDeViagemAoSistemaBancario(cartao, avisoDeViagem);
        entityManager.persist(avisoDeViagem);
        return ResponseEntity.ok().build();
    }


    private void notificaAvisoDeViagemAoSistemaBancario(Cartao cartao, AvisoDeViagem avisoDeViagem) {
        try {
            notificaAvisoDeViagem.notifica(cartao.getNumero(), new NotificaAvisoDeViagemRequest(avisoDeViagem));
        }catch (FeignException exception){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Falha ao notificar aviso de viagem.");
        }
    }


}
