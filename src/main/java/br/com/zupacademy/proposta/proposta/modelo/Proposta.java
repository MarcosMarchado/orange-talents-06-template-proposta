package br.com.zupacademy.proposta.proposta.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private BigDecimal salario;

    @Embedded
    private Endereco endereco;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento,
                    String email,
                    BigDecimal salario,
                    Endereco endereco) {

        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }
}
