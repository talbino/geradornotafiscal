package br.com.itau.geradornotafiscal.web.advice;

import br.com.itau.geradornotafiscal.service.exception.ErroNegocioException;
import br.com.itau.geradornotafiscal.web.data.response.ErroResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErroNegocioException.class)
    public ResponseEntity<Object> handleUserNotFoundException(
            ErroNegocioException exception, WebRequest request) {

        ErroResponse apiErrorMessage = new ErroResponse(null, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), 400);
    }




}
