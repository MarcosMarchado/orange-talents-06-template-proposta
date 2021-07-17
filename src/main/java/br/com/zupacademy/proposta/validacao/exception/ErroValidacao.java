package br.com.zupacademy.proposta.validacao.exception;

public class ErroValidacao {

    private String campo;

    private String mensagem;

    public ErroValidacao(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
