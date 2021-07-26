package br.com.zupacademy.proposta.utilitarios;

import javax.servlet.http.HttpServletRequest;

public class PegaIPRequisicao {

    public static String getIpRequest(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null) {
            ipAddress = request.getHeader("X_FORWARDED_FOR");
            if (ipAddress == null){
                ipAddress = request.getRemoteAddr();
            }
        }
        return ipAddress;
    }

}
