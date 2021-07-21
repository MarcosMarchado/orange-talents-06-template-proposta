package br.com.zupacademy.proposta.requisicoes.cartoes;

import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import br.com.zupacademy.proposta.proposta.repository.PropostaRepository;
import br.com.zupacademy.proposta.requisicoes.cartoes.dto.ConsultaSeCartaoFoiCriadoRequest;
import br.com.zupacademy.proposta.requisicoes.cartoes.dto.ConsultaSeCartaoFoiCriadoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class VerificaEAssociaCartaoAProposta {

    @Autowired
    private ConsultaSeCartaoFoiCriado consultaSeCartaoFoiCriado;

    @Autowired
    private PropostaRepository propostaRepository;

    @Scheduled(fixedDelay = 120000)
    private void executa(){
        List<Proposta> propostasElegiveis = propostaRepository.procuraPorPropostaElegivelSemCartao();

        propostasElegiveis.forEach(proposta -> {
            ConsultaSeCartaoFoiCriadoRequest request = new ConsultaSeCartaoFoiCriadoRequest(proposta);
            ResponseEntity<ConsultaSeCartaoFoiCriadoResponse> response = consultaSeCartaoFoiCriado.checaSeCartaoFoiCriado(request);

            String numeroDoCartao = Objects.requireNonNull(response.getBody()).getId();

            if(numeroDoCartao != null && response.getStatusCode().is2xxSuccessful()) {
                proposta.associaCartaoAProposta(new Cartao(numeroDoCartao));
                propostaRepository.save(proposta);
            }
        });
    }

}
