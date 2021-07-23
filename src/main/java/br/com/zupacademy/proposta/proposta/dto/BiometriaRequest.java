package br.com.zupacademy.proposta.proposta.dto;

import br.com.zupacademy.proposta.proposta.modelo.Biometria;
import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import br.com.zupacademy.proposta.validacao.ValidaBase64;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    @ValidaBase64
    private String fingerprint;

    @JsonCreator
    public BiometriaRequest(@NotBlank @ValidaBase64 String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Biometria converter(Cartao cartao){
        return new Biometria(this.fingerprint, cartao);
    }
}
