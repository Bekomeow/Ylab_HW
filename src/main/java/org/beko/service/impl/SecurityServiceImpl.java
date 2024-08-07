package org.beko.service.impl;

import lombok.RequiredArgsConstructor;
import org.beko.annotations.Auditable;
import org.beko.annotations.Loggable;
import org.beko.dao.UserDAO;
import org.beko.dto.TokenResponse;
import org.beko.exception.AuthorizeException;
import org.beko.exception.NotValidArgumentException;
import org.beko.exception.RegisterException;
import org.beko.model.User;
import org.beko.model.types.ActionType;
import org.beko.security.JwtTokenUtils;
import org.beko.service.SecurityService;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

/**
 * Implementation of the {@link SecurityService} interface.
 */
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserDAO userDao;
    private final JwtTokenUtils jwtTokenUtils;

    /**
     * Registers a new user with the provided login and password.
     *
     * @param login    the user's login
     * @param password the user's password
     * @return the registered user
     * @throws NotValidArgumentException if login or password is empty, blank, or does not meet length requirements
     * @throws RegisterException         if a user with the same login already exists
     */
    @Override
    @Loggable
    @Auditable(actionType = ActionType.REGISTRATION, login = "@login")
    public User register(String login, String password) {
        if (login == null || password == null || login.isEmpty() || password.isEmpty() || login.isBlank() || password.isBlank()) {
            throw new NotValidArgumentException("Пароль или логин не могут быть пустыми или состоять только из пробелов.");
        }

        if (password.length() < 5 || password.length() > 30) {
            throw new NotValidArgumentException("Длина пароля должна составлять от 5 до 30 символов.");
        }

        Optional<User> optionalUser = Optional.ofNullable(userDao.findByUsername(login));
        if (optionalUser.isPresent()) {
            throw new RegisterException("Пользователь с таким логином уже существует.");
        }

        User newUser = User.builder()
                .username(login)
                .password(password)
                .build();

        return userDao.save(newUser);
    }

    /**
     * Authorizes a user with the provided login and password.
     *
     * @param login    the user's login
     * @param password the user's password
     * @return an optional containing the authorized user, or empty if authorization fails
     * @throws AuthorizeException if the user is not found or the password is incorrect
     */
    @Override
    @Auditable(actionType = ActionType.AUTHORIZATION, login = "@login")
    public TokenResponse authorize(String login, String password) {
        Optional<User> optionalUser = Optional.ofNullable(userDao.findByUsername(login));
        if (optionalUser.isEmpty()) {
            throw new AuthorizeException("Пользователь с данным логином отсутствует в базе данных.");
        }

        if (!optionalUser.get().getPassword().equals(password)) {
            throw new AuthorizeException("Неверный пароль.");
        }

        String token = jwtTokenUtils.generateToken(login);

        return new TokenResponse(token);
    }
}