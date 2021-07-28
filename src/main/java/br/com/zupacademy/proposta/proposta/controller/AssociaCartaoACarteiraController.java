package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.dto.AssociaCartaoAUmaCarteiraRequest;
import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.proposta.modelo.Carteira;
import br.com.zupacademy.proposta.proposta.repository.CartaoRepository;
import br.com.zupacademy.proposta.validacao.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class AssociaCartaoACarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private FazAssociacaoDoCartaoComCarteira fazAssociacaoDoCartaoComCarteira;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @PostMapping("/{id}/carteira-paypal")
    public ResponseEntity<?> associaCartaoACarteiraPaypal(@PathVariable Long id,
                                                          UriComponentsBuilder uriComponentsBuilder,
                                                          @RequestBody @Valid AssociaCartaoAUmaCarteiraRequest associaCartaoAUmaCarteiraRequest){

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado"));
        /*Faz a requisição via Feign usando o método associa*/
        Carteira carteira = fazAssociacaoDoCartaoComCarteira.associa(cartao, associaCartaoAUmaCarteiraRequest, "paypal");
        entityManager.persist(carteira);
        URI uri = uriComponentsBuilder.path("/{id}/carteira-paypal/{id}").buildAndExpand(id, carteira.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
