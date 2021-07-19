package br.com.zupacademy.proposta.proposta.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private BigDecimal salario;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private StatusDaProposta statusDaProposta;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento,
                    String email,
                    BigDecimal salario,
                    Endereco endereco,
                    String nome) {

        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setStatusDaProposta(String statusDaResposta) {
        this.statusDaProposta = StatusDaProposta.getStatus(statusDaResposta);
    }
}
