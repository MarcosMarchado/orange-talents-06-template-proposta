package br.com.zupacademy.proposta.proposta.modelo;

import javax.persistence.*;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero) {
        this.numero = numero;
    }

}
