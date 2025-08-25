package co.com.pragma1.usecase.user;

import co.com.pragma1.model.user.User;
import co.com.pragma1.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<User> saveUser(User user){
        return Mono.just(user)
                .flatMap(userRepository::save);
    }
}
