package br.com.zupacademy.proposta.proposta.dto;

import br.com.zupacademy.proposta.proposta.modelo.Endereco;

public class EnderecoResponse {

    private String logradouro;

    private String bairro;

    private Integer numero;

    private String complemento;

    private String cidade;

    public EnderecoResponse(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.cidade = endereco.getCidade();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }
}
