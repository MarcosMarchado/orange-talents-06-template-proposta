package br.com.zupacademy.proposta.proposta.modelo;

import javax.persistence.*;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoDaCarteira;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String codigoDaCarteira, Cartao cartao) {
        this.codigoDaCarteira = codigoDaCarteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
