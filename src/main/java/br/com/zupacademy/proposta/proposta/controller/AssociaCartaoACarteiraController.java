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

        Carteira carteira = associaEPersiste(id, "paypal", associaCartaoAUmaCarteiraRequest);
        URI uri = uriComponentsBuilder.path("cartoes/{id}/carteira-paypal/{id}").buildAndExpand(id, carteira.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @PostMapping("/{id}/carteira-samsung-pay")
    public ResponseEntity<?> associaCartaoACarteiraSamsungPay(@PathVariable Long id,
                                                              UriComponentsBuilder uriComponentsBuilder,
                                                              @RequestBody @Valid AssociaCartaoAUmaCarteiraRequest associaCartaoAUmaCarteiraRequest){

        Carteira carteira = associaEPersiste(id, "samsung-pay", associaCartaoAUmaCarteiraRequest);
        URI uri = uriComponentsBuilder.path("cartoes/{id}/samsung-pay/{id}").buildAndExpand(id, carteira.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*Associa o Cartão a Carteira através da Api de Cartões e faz a Persistencia da Carteira.*/
    private Carteira associaEPersiste(Long id,
                                      String nomeDaCarteira,
                                      AssociaCartaoAUmaCarteiraRequest associaCartaoAUmaCarteiraRequest) {

        Cartao cartao = cartaoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado"));
        /*Faz a requisição via Feign usando o método associa*/
        Carteira carteira = fazAssociacaoDoCartaoComCarteira.associa(cartao, associaCartaoAUmaCarteiraRequest, nomeDaCarteira);
        entityManager.persist(carteira);
        return carteira;
    }


}
