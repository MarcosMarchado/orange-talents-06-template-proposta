package br.com.zupacademy.proposta.proposta.controller;

import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.proposta.repository.CartaoRepository;
import br.com.zupacademy.proposta.validacao.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cartoes")
public class BloqueiaCartao {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/{id}/bloqueio")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable Long id,
                                            HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");
        String ipRequest = getIpRequest(request);

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado"));

        cartao.associaBloqueioAoCartao(userAgent, ipRequest);
        cartaoRepository.save(cartao);
        return ResponseEntity.ok().build();
    }


    private String getIpRequest(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null) {
            ipAddress = request.getHeader("X_FORWARDED_FOR");
            if (ipAddress == null){
                ipAddress = request.getRemoteAddr();
            }
        }
        return ipAddress;
    }
}
