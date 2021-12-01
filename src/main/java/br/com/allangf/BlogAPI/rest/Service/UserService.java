package br.com.allangf.BlogAPI.rest.Service;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.rest.config.dto.CredentialsDTO;
import br.com.allangf.BlogAPI.rest.config.dto.TagDTO;
import br.com.allangf.BlogAPI.rest.config.dto.TokenDTO;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    User createNewUser(UserDTO userDTO);

    List<User> allUser();

    void deleteUser(int userId);

    public TokenDTO authenticate(CredentialsDTO credentialsDTO);

}
