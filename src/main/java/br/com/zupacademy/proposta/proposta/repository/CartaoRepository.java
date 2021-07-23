package br.com.zupacademy.proposta.proposta.repository;

import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
