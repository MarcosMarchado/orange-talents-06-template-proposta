package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.dto.NovoAvisoDeViagemRequest;
import br.com.zupacademy.proposta.proposta.modelo.AvisoDeViagem;
import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.proposta.repository.CartaoRepository;
import br.com.zupacademy.proposta.utilitarios.PegaIPRequisicao;
import br.com.zupacademy.proposta.validacao.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        entityManager.persist(avisoDeViagem);
        return ResponseEntity.ok().build();
    }



}
