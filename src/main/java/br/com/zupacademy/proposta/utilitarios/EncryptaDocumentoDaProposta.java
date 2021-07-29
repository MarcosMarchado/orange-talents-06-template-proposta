package br.com.zupacademy.proposta.utilitarios;

import org.springframework.security.crypto.encrypt.Encryptors;

public class EncryptaDocumentoDaProposta {
    public static final String salt = "5f5f47287021365a757b2667743b452b445a75433172454b6c734e5e2b7e332a";

    public static String encryptar(String documento){
        return Encryptors.queryableText("document", salt).encrypt(documento);
    }

    public static String descriptografa(String documento){
        return Encryptors.queryableText("document", salt).decrypt(documento);
    }
}
