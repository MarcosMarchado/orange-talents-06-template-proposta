package br.com.zupacademy.proposta.proposta.repository;

import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);
}
