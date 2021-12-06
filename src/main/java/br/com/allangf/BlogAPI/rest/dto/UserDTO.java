package br.com.allangf.BlogAPI.rest.dto;

import br.com.allangf.BlogAPI.rest.Errors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull(message = Errors.LOGIN_IS_REQUIRED)
    private String login;
    @NotNull(message = Errors.PASSWORD_IS_REQUIRED)
    private String password;
    @NotNull(message = Errors.NAME_IS_REQUIRED)
    private String name;
    @NotNull(message = Errors.BIRTH_DATE_IS_REQUIRED)
    private String birthDate;
    @NotNull(message = Errors.EMAIL_IS_REQUIRED)
    private String email;

}
