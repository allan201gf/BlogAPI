package br.com.allangf.BlogAPI.rest.Service.impl;

import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.domain.repository.UserRepository;
import br.com.allangf.BlogAPI.rest.Helpers;
import br.com.allangf.BlogAPI.rest.Service.UserService;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User createNewUser(UserDTO userDTO) {
        User user = new User();
        user.setBirthDate(Helpers.stringForDate(userDTO.getBirthDate()));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user);
    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }

}
