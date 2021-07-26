package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.proposta.repository.CartaoRepository;
import br.com.zupacademy.proposta.requisicoes.cartoes.NotificaBloqueioDoCartao;
import br.com.zupacademy.proposta.utilitarios.PegaIPRequisicao;
import br.com.zupacademy.proposta.validacao.exception.ObjectNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/cartoes")
public class BloqueiaCartao {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private NotificaBloqueioDoCartao notificaBloqueioDoCartao;

    @PostMapping("/{id}/bloqueio")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable Long id,
                                            HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");
        String ipRequest = PegaIPRequisicao.getIpRequest(request);

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado"));

        cartao.associaBloqueioAoCartao(userAgent, ipRequest);
        notificaBloqueio(cartao);
        cartaoRepository.save(cartao);
        return ResponseEntity.ok().build();
    }

    private void notificaBloqueio(Cartao cartao) {
        try {
            notificaBloqueioDoCartao.notifica(cartao.getNumero(), Map.of("sistemaResponsavel", "Api-proposta"));
        }catch (FeignException exception){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Falha ao notificar bloqueio.");
        }
    }

}
