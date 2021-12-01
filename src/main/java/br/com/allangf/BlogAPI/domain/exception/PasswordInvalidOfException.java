package br.com.allangf.BlogAPI.domain.exception;

import br.com.allangf.BlogAPI.rest.Errors;

public class PasswordInvalidOfException extends RuntimeException {

    public PasswordInvalidOfException(String message) {
        super(Errors.PASSWORD_IS_WRONG);
    }

}
