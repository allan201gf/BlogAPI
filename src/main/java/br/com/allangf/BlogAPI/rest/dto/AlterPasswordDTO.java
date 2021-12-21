package br.com.allangf.BlogAPI.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlterPasswordDTO {

    @NotEmpty
    @JsonProperty("current_password")
    private String currentPassword;

    @NotEmpty
    @JsonProperty("new_password")
    private String newPassword;

    @NotEmpty
    @JsonProperty("confirm_new_password")
    private String confirmNewPassword;


}
