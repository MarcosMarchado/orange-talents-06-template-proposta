package br.com.zupacademy.proposta.requisicoes.cartoes.dto;

import br.com.zupacademy.proposta.proposta.modelo.Cartao;

public class ConsultaSeCartaoFoiCriadoResponse {
    private String id;

    public String getId() {
        return id;
    }

    public Cartao converter(){
        return new Cartao(this.id);
    }
}
