package br.com.allangf.BlogAPI.rest.Service;

import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.rest.config.dto.CredentialsDTO;
import br.com.allangf.BlogAPI.rest.config.dto.TokenDTO;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createNewUser(UserDTO userDTO);

    List<User> allUser();

    void deleteUser();

    TokenDTO authenticate(CredentialsDTO credentialsDTO);

    Optional<User> getUserLogged();

}
