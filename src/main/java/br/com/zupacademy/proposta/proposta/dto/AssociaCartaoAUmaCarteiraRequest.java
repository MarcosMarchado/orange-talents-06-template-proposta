package br.com.zupacademy.proposta.proposta.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class AssociaCartaoAUmaCarteiraRequest {

    @NotBlank
    private String email;

    @JsonCreator
    public AssociaCartaoAUmaCarteiraRequest(@NotBlank String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
