package br.com.santanajs.zipcode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT) // 204
public class NoContentException extends Exception{
  
}
