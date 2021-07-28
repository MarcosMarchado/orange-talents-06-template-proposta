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
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

@Component
public class VerificaEAssociaCartaoAProposta {

    @Autowired
    private ConsultaSeCartaoFoiCriado consultaSeCartaoFoiCriado;

    @Autowired
    private PropostaRepository propostaRepository;

    @Scheduled(fixedDelay = 120000)
    protected void executa(){
        List<Proposta> propostasElegiveis = propostaRepository.procuraPorPropostaElegivelSemCartao();

        propostasElegiveis.forEach(proposta -> {
            ConsultaSeCartaoFoiCriadoRequest request = new ConsultaSeCartaoFoiCriadoRequest(proposta);
            ResponseEntity<ConsultaSeCartaoFoiCriadoResponse> response = consultaSeCartaoFoiCriado.checaSeCartaoFoiCriado(request);

            Cartao cartao = Objects.requireNonNull(response.getBody()).converter();

            if(response.getStatusCode().is2xxSuccessful()) {
                Assert.isTrue(cartao != null, "Cartão não pode estar nulo.");
                proposta.associaCartaoAProposta(cartao);
                propostaRepository.save(proposta);
            }
        });
    }

}
