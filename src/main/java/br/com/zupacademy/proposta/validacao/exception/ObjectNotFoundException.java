package br.com.zupacademy.proposta.validacao.exception;

public class ObjectNotFoundException extends RuntimeException{
    private String msg;

    public ObjectNotFoundException(String msg) {
        super(msg);

    }
}
