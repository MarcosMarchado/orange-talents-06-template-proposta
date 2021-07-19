package br.com.zupacademy.proposta.proposta.dto;

import br.com.zupacademy.proposta.proposta.modelo.Endereco;
import br.com.zupacademy.proposta.proposta.modelo.Proposta;
import br.com.zupacademy.proposta.validacao.Documento;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    @Documento
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Positive
    private BigDecimal salario;
    @Valid
    private EnderecoRequest enderecoRequest;
    @NotBlank
    private String nome;

    public NovaPropostaRequest(
            @Documento String documento,
            @NotBlank @Email String email,
            @NotNull @Positive BigDecimal salario,
            @Valid EnderecoRequest enderecoRequest,
            @NotBlank String nome) {

        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.enderecoRequest = enderecoRequest;
        this.nome = nome;
    }

    public EnderecoRequest getEnderecoRequest() {
        return enderecoRequest;
    }

    public String getDocumento() {
        return documento;
    }

    public Proposta converter(){
        Endereco endereco = enderecoRequest.converter();
        return new Proposta(this.documento, this.email, this.salario, endereco, this.nome);
    }
}
