package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.dto.BiometriaRequest;
import br.com.zupacademy.proposta.proposta.modelo.Biometria;
import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.proposta.repository.CartaoRepository;
import br.com.zupacademy.proposta.validacao.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CadastroBiometria {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<?> cadastraBiometria(@PathVariable Long id,
                                               @Valid @RequestBody BiometriaRequest biometriaRequest,
                                               UriComponentsBuilder uriComponentsBuilder) {


        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado."));

        Biometria biometria = biometriaRequest.converter(cartao);
        cartao.associaBiometriaAoCartao(biometria);
        entityManager.persist(biometria);

        URI uri = uriComponentsBuilder.path("/cartoes/{id}/biometrias/{id}")
                .buildAndExpand(id, biometria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
