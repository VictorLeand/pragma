package co.com.pragma1.r2dbc;

import co.com.pragma1.model.user.User;
import co.com.pragma1.model.user.gateways.UserRepository;
import co.com.pragma1.r2dbc.entity.UserEntity;
import co.com.pragma1.r2dbc.helper.ReactiveAdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserEntity,
        Long,
        UserReactiveRepository
> implements UserRepository {
    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.map(entity, User.class));
    }

    @Transactional
    @Override
    public Mono<User> save(User user) {
        return Mono.just(user)
                .doOnNext(usuario -> log.info("Iniciando guardado de usuario : {}" , usuario))
                .flatMap(super::save)
                .doOnNext(usuario -> log.info("Usuario guardado : {}" , usuario));
    }

}
