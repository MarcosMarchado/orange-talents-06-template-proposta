package br.com.zupacademy.proposta.proposta.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fingerprint;

    private LocalDateTime dataAssociacao = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria(){
    }

    public Biometria(String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria = (Biometria) o;
        return Objects.equals(fingerprint, biometria.fingerprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fingerprint);
    }
}
