package br.com.allangf.BlogAPI.rest.dto;

import br.com.allangf.BlogAPI.rest.Errors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    @Email
    @NotNull(message = Errors.EMAIL_IS_REQUIRED)
    private String email;

}
