package br.com.zupacademy.proposta.validacao.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> mensagens = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemErro = String.format("O campo %s %s", fieldError.getField(), mensagem);
            mensagens.add(mensagemErro);
        });
        return ResponseEntity.badRequest().body(new ErroPadrao(mensagens));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroPadrao> responseStatusException(ResponseStatusException exception){
        List<String> mensagens = new ArrayList<>();
        mensagens.add(exception.getReason());
        ErroPadrao erroPadrao = new ErroPadrao(mensagens);
        return ResponseEntity.status(exception.getStatus()).body(erroPadrao);
    }

}
