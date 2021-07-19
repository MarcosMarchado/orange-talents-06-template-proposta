package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.dto.NovaPropostaRequest;
import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import br.com.zupacademy.proposta.proposta.repository.PropostaRepository;
import br.com.zupacademy.proposta.requisicoes.solicitacao.ConsultaDadosDoSolicitante;
import br.com.zupacademy.proposta.requisicoes.solicitacao.dto.ConsultaDadosRequest;
import br.com.zupacademy.proposta.requisicoes.solicitacao.dto.ConsultaDadosResponse;
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
    private ConsultaDadosDoSolicitante consultaDadosDoSolicitante;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest,
                                           UriComponentsBuilder uriComponentsBuilder){

        String documento = novaPropostaRequest.getDocumento();
        boolean possuiProposta = propostaRepository.findByDocumento(documento).isPresent();
        if(possuiProposta) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Solicitante já tem um proposta");

        Proposta proposta = novaPropostaRequest.converter();
        propostaRepository.save(proposta);
        URI uri = uriComponentsBuilder.path("/propostas/{ id }")
                .buildAndExpand(proposta.getId())
                .toUri();

        ConsultaDadosResponse solicitacao = consultaDadosDoSolicitante.consulta(new ConsultaDadosRequest(proposta));
        proposta.setStatusDaProposta(solicitacao.getResultadoSolicitacao());

        return ResponseEntity.created(uri).build();
    }
}
