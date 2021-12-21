package br.com.allangf.BlogAPI.rest.Service.impl;

import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.domain.exception.PasswordInvalidOfException;
import br.com.allangf.BlogAPI.domain.exception.RuleOfException;
import br.com.allangf.BlogAPI.domain.repository.UserRepository;
import br.com.allangf.BlogAPI.rest.Errors;
import br.com.allangf.BlogAPI.rest.Helpers;
import br.com.allangf.BlogAPI.rest.Service.UserService;
import br.com.allangf.BlogAPI.rest.config.Roles;
import br.com.allangf.BlogAPI.rest.config.jwt.JwtService;
import br.com.allangf.BlogAPI.rest.dto.AlterPasswordDTO;
import br.com.allangf.BlogAPI.rest.dto.CredentialsDTO;
import br.com.allangf.BlogAPI.rest.dto.TokenDTO;
import br.com.allangf.BlogAPI.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

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

        List<User> existUser = userRepository.findByEmailOrLogin(userDTO.getEmail(), userDTO.getLogin());

        if (!existUser.isEmpty()) {
            throw new RuleOfException(Errors.EMAIL_OR_LOGIN_ALREADY_REGISTERED);
        }

        User user = new User();
        user.setBirthDate(Helpers.stringForDate(userDTO.getBirthDate()));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Roles.USER);

        return userRepository.save(user);
    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser() {
        try {
            Optional<User> loggedUser = getUserLogged();

            userRepository.deleteById(loggedUser.get().getUserId());
        } catch (EmptyResultDataAccessException e) {
            throw new RuleOfException(Errors.USER_NOT_FOUND);
        } catch (Exception e) {
            throw new RuleOfException(Errors.UNEBLE_DELETE_USER_ASSIGNED_POST);
        }
    }

    @Override
    public TokenDTO authenticate(CredentialsDTO credentialsDTO) {
        try {
            User user = User.builder()
                    .login(credentialsDTO.getLogin())
                    .password(credentialsDTO.getPassword())
                    .build();
            UserDetails authenticatedUser = userDetailsService.authenticate(user);

            String token = jwtService.generateToken(user);

            return new TokenDTO(user.getLogin(), token);
        } catch (UsernameNotFoundException | PasswordInvalidOfException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public Optional<User> getUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByLogin(authentication.getName());
    }

    @Override
    public void alterPassword(AlterPasswordDTO alterPasswordDTO) {
        Optional<User> user = getUserLogged();
        if (user.isEmpty()) {
            throw new RuleOfException(Errors.USER_NOT_FOUND);
        }

        if (!alterPasswordDTO.getNewPassword().equals(alterPasswordDTO.getConfirmNewPassword())) {
            throw new RuleOfException(Errors.NEW_PASSWORD_DONT_CHECK);
        }

        boolean checkPassword = passwordEncoder.matches(alterPasswordDTO.getCurrentPassword(), user.get().getPassword());

        if (checkPassword) {
            user.get().setPassword(passwordEncoder.encode(alterPasswordDTO.getNewPassword()));
            userRepository.save(user.get());
        } else {
            throw new RuleOfException(Errors.CURRENT_PASSWORD_INVALID);
        }
    }
}
