package co.com.pragma1.model.user.gateways;

import co.com.pragma1.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<Boolean> existsByCorreoElectronico(String correoElectronico);
}
