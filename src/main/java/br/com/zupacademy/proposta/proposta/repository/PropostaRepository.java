package br.com.zupacademy.proposta.proposta.repository;

import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    @Query("SELECT p FROM Proposta p WHERE p.statusDaProposta = 'ELEGIVEL' AND p.cartao = null")
    List<Proposta> procuraPorPropostaElegivelSemCartao();
}
