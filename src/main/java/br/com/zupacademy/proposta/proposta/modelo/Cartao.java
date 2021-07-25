package br.com.zupacademy.proposta.proposta.modelo;

import br.com.zupacademy.proposta.validacao.exception.BiometriaRepetidaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero) {
        this.numero = numero;
        this.statusCartao = StatusCartao.DESBLOQUEADO;
    }

    public void associaBiometriaAoCartao(Biometria biometria) {
        Set<Biometria> biometriasRepetidas = this.biometrias.stream()
                .filter(biometriaAtual -> biometriaAtual.equals(biometria))
                .collect(Collectors.toSet());
        if(!biometriasRepetidas.isEmpty()) throw new BiometriaRepetidaException("Biometria já cadastrada para este cartão.");
        this.biometrias.add(biometria);
    }

    public void associaBloqueioAoCartao(String userAgent, String ipRequest){
        this.cartaoEstaBloqueado();
        this.bloqueios.add(new Bloqueio(userAgent, ipRequest, this));
    }

    //Se estiver bloqueado lançará um exceção
    private void cartaoEstaBloqueado(){
        if(statusCartao.equals(StatusCartao.BLOQUEADO))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está bloqueado");
    }

}
