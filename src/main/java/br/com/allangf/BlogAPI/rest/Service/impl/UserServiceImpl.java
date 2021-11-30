package br.com.allangf.BlogAPI.rest.Service.impl;

import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.domain.exception.RuleOfException;
import br.com.allangf.BlogAPI.domain.repository.UserRepository;
import br.com.allangf.BlogAPI.rest.Errors;
import br.com.allangf.BlogAPI.rest.Helpers;
import br.com.allangf.BlogAPI.rest.Service.UserService;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createNewUser(UserDTO userDTO) {

        if (userDTO.getEmail() == null) {
            throw new RuleOfException(Errors.EMAIL_IS_REQUIRED);
        }
        if (userDTO.getBirthDate() == null) {
            throw new RuleOfException(Errors.BIRTH_DATE_IS_REQUIRED);
        }
        if (userDTO.getName() == null) {
            throw new RuleOfException(Errors.NAME_IS_REQUIRED);
        }

        List<User> existUser = userRepository.findByEmail(userDTO.getEmail());

        if (!existUser.isEmpty()) {
            throw new RuleOfException(Errors.EMAIL_ALREADY_REGISTERED);
        }

        User user = new User();
        user.setBirthDate(Helpers.stringForDate(userDTO.getBirthDate()));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    @Override
    public void createUserList(List<UserDTO> userDTO) {
        userDTO.forEach(this::createNewUser);
    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuleOfException(Errors.USER_NOT_FOUND);
        }

    }

}
