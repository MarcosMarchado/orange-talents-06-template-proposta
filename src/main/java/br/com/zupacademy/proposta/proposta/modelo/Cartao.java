package br.com.zupacademy.proposta.proposta.modelo;

import br.com.zupacademy.proposta.validacao.exception.BiometriaRepetidaException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @OneToMany(mappedBy = "cartao")
    private Set<Biometria> biometrias = new HashSet<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero) {
        this.numero = numero;
    }

    public Set<Biometria> getBiometrias() {
        return biometrias;
    }

    public void associaBiometriaAoCartao(Biometria biometria) {
        Set<Biometria> biometriasRepetidas = this.biometrias.stream()
                .filter(biometriaAtual -> biometriaAtual.equals(biometria))
                .collect(Collectors.toSet());
        if(!biometriasRepetidas.isEmpty()) throw new BiometriaRepetidaException("Biometria já cadastrada para este cartão.");
        this.biometrias.add(biometria);
    }

    public Long getId() {
        return id;
    }
}
