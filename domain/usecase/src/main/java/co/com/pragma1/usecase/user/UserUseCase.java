package co.com.pragma1.usecase.user;

import co.com.pragma1.model.user.User;
import co.com.pragma1.model.user.exception.BusinessException;
import co.com.pragma1.model.user.gateways.UserRepository;
import reactor.core.publisher.Mono;


public class UserUseCase {


    private final UserRepository userRepository;

    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    private Mono<User> validarDocumento(User user) {
        return userRepository.existsByDocumento(user.getDocumento())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new BusinessException("El documento ya está registrado"));
                    }
                    return Mono.just(user); });
    }

    private Mono<User> validarCorreo(User user) {
        return userRepository.existsByEmail(user.getEmail())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new BusinessException("El correo electrónico ya está registrado"));
                    }
                    return Mono.just(user); });
    }

    public Mono<User> saveUser(User user) {
        return Mono.just(user)
                .flatMap(this::validarDocumento)
                .flatMap(this::validarCorreo)
                .flatMap(userRepository::save);
    }
}
