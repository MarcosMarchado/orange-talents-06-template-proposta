package br.com.zupacademy.proposta.proposta.modelo;

public enum StatusDaProposta {
    NAO_ELEGIVEL,
    ELEGIVEL;

    public static StatusDaProposta getStatus(String statusDaResposta) {
        if(statusDaResposta.equals("SEM_RESTRICAO")){
            return ELEGIVEL;
        }
        return NAO_ELEGIVEL;
    }
}
