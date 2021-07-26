package br.com.zupacademy.proposta.proposta.dto;

import br.com.zupacademy.proposta.proposta.modelo.AvisoDeViagem;
import br.com.zupacademy.proposta.proposta.modelo.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoAvisoDeViagemRequest {

    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDoTermino;

    @JsonCreator
    public NovoAvisoDeViagemRequest(@NotBlank String destino,
                                    @NotNull @Future LocalDate dataDoTermino) {

        this.destino = destino;
        this.dataDoTermino = dataDoTermino;
    }

    public AvisoDeViagem converter(String userAgent, String ipRequest, Cartao cartao){
        return new AvisoDeViagem(this.destino, this.dataDoTermino, userAgent, ipRequest, cartao);
    }

}
