package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.dto.PropostaConsultaResponse;
import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import br.com.zupacademy.proposta.proposta.repository.PropostaRepository;
import br.com.zupacademy.proposta.validacao.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propostas")
public class VerificaInfDaProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<PropostaConsultaResponse> verificarProposta(@PathVariable Long id){
        Proposta proposta = propostaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Proposta com o identificado " + id + " não encontrada."));

        return ResponseEntity.ok().body(new PropostaConsultaResponse(proposta));
    }

}
