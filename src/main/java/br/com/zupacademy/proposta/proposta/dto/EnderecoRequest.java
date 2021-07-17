package br.com.zupacademy.proposta.proposta.dto;

import br.com.zupacademy.proposta.proposta.modelo.Endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoRequest {
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    private String complemento;
    @NotNull
    private Integer numero;
    @NotBlank
    private String cidade;

    public EnderecoRequest(@NotBlank String logradouro,
                           @NotBlank String bairro,
                           @NotBlank String complemento,
                           @NotNull Integer numero,
                           @NotBlank String cidade) {

        this.logradouro = logradouro;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public Endereco converter(){
        return new Endereco(this.logradouro, this.bairro, this.numero, this.complemento, this.cidade);
    }

}
