package br.com.allangf.BlogAPI.rest.controller;


import br.com.allangf.BlogAPI.domain.entity.User;

import br.com.allangf.BlogAPI.rest.Service.UserService;

import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

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

    @ApiOperation("Delete user by Id")
    @DeleteMapping("/v1/delete/id/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }


}
