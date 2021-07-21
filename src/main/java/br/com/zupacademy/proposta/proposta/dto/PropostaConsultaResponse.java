package br.com.zupacademy.proposta.proposta.dto;

import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import br.com.zupacademy.proposta.proposta.modelo.StatusDaProposta;

public class PropostaConsultaResponse {

    private String nome;

    private String documento;

    private String email;

    private EnderecoResponse endereco;

    private StatusDaProposta statusDaProposta;

    public PropostaConsultaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.statusDaProposta = proposta.getStatusDaProposta();
        this.endereco = new EnderecoResponse(proposta.getEndereco());
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public EnderecoResponse getEndereco() {
        return endereco;
    }

    public StatusDaProposta getStatusDaProposta() {
        return statusDaProposta;
    }

}
