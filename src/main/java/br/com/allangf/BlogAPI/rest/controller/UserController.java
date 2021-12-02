package br.com.allangf.BlogAPI.rest.controller;


import br.com.allangf.BlogAPI.domain.entity.User;

import br.com.allangf.BlogAPI.rest.Service.UserService;

import br.com.allangf.BlogAPI.rest.config.dto.CredentialsDTO;
import br.com.allangf.BlogAPI.rest.config.dto.TokenDTO;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import br.com.allangf.BlogAPI.rest.config.jwt.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private JwtService jwtService;

    @ApiOperation("Create new user")
    @PostMapping("/v1")
    public User createNewUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createNewUser(userDTO);
    }

    @ApiOperation("All user")
    @GetMapping("/v1")
    public List<User> allUser() {
        return userService.allUser();
    }

    @ApiOperation("Delete user logged")
    @DeleteMapping("/v1/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser() {
        userService.deleteUser();
    }

    @PostMapping("/v1/login")
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentialsDTO) {
        return userService.authenticate(credentialsDTO);
    }

    @GetMapping("/v1/getloggeduser")
    public Optional<User> getUserLogged () {
        return userService.getUserLogged();
    }

}
