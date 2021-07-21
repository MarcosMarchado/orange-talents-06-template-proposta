package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.dto.NovaPropostaRequest;
import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import br.com.zupacademy.proposta.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class NovaProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private VerificaStatus verificaStatus;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest,
                                           UriComponentsBuilder uriComponentsBuilder){

        Proposta proposta = novaPropostaRequest.converter();
        boolean possuiProposta = propostaRepository.findByDocumento(proposta.getDocumento()).isPresent();
        if(possuiProposta) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Solicitante já tem um proposta");

        propostaRepository.save(proposta);
        URI uri = uriComponentsBuilder.path("/propostas/{ id }")
                .buildAndExpand(proposta.getId())
                .toUri();

        String status = verificaStatus.verifica(proposta);
        proposta.setStatusDaProposta(status);
        if(status.equals("COM_RESTRICAO")) return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.created(uri).build();
    }
}
