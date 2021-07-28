package br.com.zupacademy.proposta.requisicoes.cartoes.dto;

public class AssociaCarteiraRequest {

    private String email;

    private String carteira;

    public AssociaCarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
