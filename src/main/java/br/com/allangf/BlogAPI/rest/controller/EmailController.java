package br.com.allangf.BlogAPI.rest.controller;


import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.rest.Service.EmailService;
import br.com.allangf.BlogAPI.rest.Service.UserService;
import br.com.allangf.BlogAPI.rest.config.dto.CredentialsDTO;
import br.com.allangf.BlogAPI.rest.config.dto.EmailDTO;
import br.com.allangf.BlogAPI.rest.config.dto.TokenDTO;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import br.com.allangf.BlogAPI.rest.config.jwt.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailController {

    private EmailService emailService;

    @ApiOperation("Add email to list send")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/v1")
    public void addEmail(@Valid @RequestBody EmailDTO emailDTO) {
        emailService.addEmailList(emailDTO);
    }

    @ApiOperation("Delete email list")
    @DeleteMapping("/v1")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmail(@Valid @RequestBody EmailDTO emailDTO) {
        emailService.removeEmailList(emailDTO);
    }

}
