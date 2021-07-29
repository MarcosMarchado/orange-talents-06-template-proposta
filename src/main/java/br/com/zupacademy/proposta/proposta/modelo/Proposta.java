package br.com.zupacademy.proposta.proposta.modelo;

import br.com.zupacademy.proposta.utilitarios.EncryptaDocumentoDaProposta;

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

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento,
                    String email,
                    BigDecimal salario,
                    Endereco endereco,
                    String nome) {

        this.documento = EncryptaDocumentoDaProposta.encryptar(documento);
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

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public StatusDaProposta getStatusDaProposta() {
        return statusDaProposta;
    }

    public String getDocumento() {
        return EncryptaDocumentoDaProposta.descriptografa(this.documento);
    }

    public void setStatusDaProposta(String statusDaResposta) {
        this.statusDaProposta = StatusDaProposta.getStatus(statusDaResposta);
    }

    public void associaCartaoAProposta(Cartao cartao){
        this.cartao = cartao;
    }
}
