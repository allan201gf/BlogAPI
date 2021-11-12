package br.com.allangf.BlogAPI.rest.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String birthDate;
    private String email;

}
