package br.com.zupacademy.proposta.requisicoes.cartoes.dto;

import br.com.zupacademy.proposta.proposta.modelo.AvisoDeViagem;

import java.time.LocalDate;

public class NotificaAvisoDeViagemRequest {

    private LocalDate validoAte;

    private String destino;

    public NotificaAvisoDeViagemRequest(AvisoDeViagem avisoDeViagem) {
        this.validoAte = avisoDeViagem.getDataDoTermino();
        this.destino = avisoDeViagem.getDestino();
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
