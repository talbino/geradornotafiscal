package br.com.itau.geradornotafiscal.service.exception;

public class ErroNegocioException extends RuntimeException {

    public ErroNegocioException(String mensagem) {
        super(mensagem);
    }

}
