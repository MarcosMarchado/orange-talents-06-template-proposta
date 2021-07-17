package br.com.zupacademy.proposta.proposta.repository;

import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
