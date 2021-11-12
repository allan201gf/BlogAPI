package br.com.allangf.BlogAPI.rest.controller;


import br.com.allangf.BlogAPI.domain.entity.User;

import br.com.allangf.BlogAPI.rest.Service.UserService;

import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @ApiOperation("Create new user")
    @PostMapping("/v1")
    public User createNewUser(@RequestBody UserDTO userDTO) {
        return userService.createNewUser(userDTO);
    }

    @GetMapping("/v1")
    public List<User> allTag() {
        return userService.allUser();
    }


}
