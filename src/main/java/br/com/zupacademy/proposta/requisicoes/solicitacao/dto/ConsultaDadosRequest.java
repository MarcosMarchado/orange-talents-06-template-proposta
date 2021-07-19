package br.com.zupacademy.proposta.requisicoes.solicitacao.dto;

import br.com.zupacademy.proposta.proposta.modelo.Proposta;

public class ConsultaDadosRequest {

    private String documento;

    private String nome;

    private Long idProposta;

    public ConsultaDadosRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
