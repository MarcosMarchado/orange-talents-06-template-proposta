package br.com.zupacademy.proposta.proposta.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoDeViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;

    private LocalDate dataDoTermino;

    private String userAgent;

    private LocalDateTime criadoEm = LocalDateTime.now();

    private String ipRequest;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoDeViagem() {
    }

    public AvisoDeViagem(String destino,
                         LocalDate dataDoTermino,
                         String userAgent,
                         String ipRequest,
                         Cartao cartao) {

        this.destino = destino;
        this.dataDoTermino = dataDoTermino;
        this.userAgent = userAgent;
        this.ipRequest = ipRequest;
        this.cartao = cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataDoTermino() {
        return dataDoTermino;
    }
}
