package br.com.zupacademy.proposta.proposta.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userAgent;

    private String ipRequest;

    @ManyToOne
    private Cartao cartao;

    private LocalDateTime bloqueadoEm = LocalDateTime.now();

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String userAgent, String ipRequest, Cartao cartao) {
        this.userAgent = userAgent;
        this.ipRequest = ipRequest;
        this.cartao = cartao;
    }

}
