package co.com.pragma1.usecase.user;

import co.com.pragma1.model.user.User;
import co.com.pragma1.model.user.exception.BusinessException;
import co.com.pragma1.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


import java.math.BigDecimal;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;


    private Mono<User> validarCorreo(User user) {
        return userRepository.existsByCorreoElectronico(user.getCorreoElectronico())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new BusinessException("El correo electrónico ya está registrado"));
                    }
                    return Mono.just(user); });
    }

    public Mono<User> saveUser(User user) {
        return Mono.just(user)
                .flatMap(this::validarCorreo)
                .flatMap(userRepository::save);
    }
}
